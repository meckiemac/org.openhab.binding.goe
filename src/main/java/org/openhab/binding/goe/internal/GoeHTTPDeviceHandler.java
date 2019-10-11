package org.openhab.binding.goe.internal;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.http.HttpMethod;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.ThingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Written by a programming noob, be careful with usage.
 *
 * @author Andreas Merk
 *
 */
public class GoeHTTPDeviceHandler extends GoeAbstractHandler {

    private final Logger logger = LoggerFactory.getLogger(GoeHTTPDeviceHandler.class);

    private @Nullable HttpClient httpClient;
    private URI deviceURI;

    private ScheduledFuture<?> refreshJob;
    private ScheduledFuture<?> refreshJobCharging;

    private Date nextFullupdate = new Date(System.currentTimeMillis());

    /**
     *
     * @param thing
     * @param httpClient an active HttpClient
     */
    public GoeHTTPDeviceHandler(Thing thing, @Nullable HttpClient httpClient) {
        super(thing);
        // TODO Auto-generated constructor stub

        if (httpClient != null) {
            this.httpClient = httpClient;
        } else {
            logger.warn("A running http client is required! httpClient is null");
        }
        logger.info("Initializing HttpHandler");
    }

    @Override
    protected void deviceCommand(String command, String value) throws Exception {

        ContentResponse cr = null;
        JsonObject updatedDeviceStatus = null;

        cr = this.sendRequest("/mqtt?payload=" + command + "=" + value);

        // TODO check http codes before return
        if (cr != null) {
            updatedDeviceStatus = new JsonParser().parse(cr.getContentAsString()).getAsJsonObject();
        } else {
            throw new Exception();
        }

        if (updatedDeviceStatus != null) {
            if (value.equals(updatedDeviceStatus.get(command).getAsString())) {

                // TODO Check distance to next update (safety window)
                updateThingStatus(updatedDeviceStatus, false);
            } else {
                logger.warn("Update of {} with value {} failed. Charger kept old state {} ", command, value,
                        updatedDeviceStatus.get(command).getAsString());
                throw new RuntimeException();
            }
        }

    }

    @Override
    public void initialize() {

        logger.info("Initialize Component.");
        updateStatus(ThingStatus.UNKNOWN);

        if (!checkConfig()) {
            updateStatus(ThingStatus.OFFLINE);
            logger.warn("Configuration check, something went wrong.");
            return;
        }

        this.restartRefreshJob();

    }

    @Override
    public void dispose() {
        if (this.refreshJob != null) {
            this.refreshJob.cancel(true);
            this.refreshJob = null;
        }
        super.dispose();
    }

    /**
     * Runs the regular full data refreshjob;
     */
    private void restartRefreshJob() {

        // cleanup
        if (this.refreshJob != null) {
            this.refreshJob.cancel(true);
            this.refreshJob = null;
        }

        this.refreshJob = this.scheduler.scheduleWithFixedDelay(() -> {

            try {

                // set next execution time for calculating other jobs.
                this.nextFullupdate.setTime(this.nextFullupdate.getTime() + this.refreshIntervall.longValue() * 1000);

                JsonObject newStatusObject = this.getDeviceStatusJson();

                // simple check for time field and then transfer
                if (newStatusObject.get("tme") != null) {

                    // set online if not
                    if (this.getThing().getStatus() != ThingStatus.ONLINE) {
                        this.updateStatus(ThingStatus.ONLINE);
                    }

                    // transfer new status
                    updateThingStatus(newStatusObject, true);

                } else {

                    // now content -> thing offline
                    this.updateStatus(ThingStatus.OFFLINE);
                }

            } catch (Exception e) {

                // TODO Auto-generated catch block
                this.updateStatus(ThingStatus.OFFLINE);
                e.printStackTrace();

            }

        }, 0L, this.refreshIntervall.longValue(), TimeUnit.SECONDS);

    }

    // gets called every time we initialize a previous clean up
    private boolean checkConfig() {

        URI myUri = null;
        GoeConfiguration myCfg = null;

        logger.warn("Checking configuration.");
        // clear explicit old configuration (could be also a configuration update
        if (config != null) {
            config = null;
        }

        config = getConfigAs(GoeConfiguration.class);

        if (config != null) {
            myCfg = config;
        } else {
            logger.warn("Configuration is null");
        }

        if (myCfg.ipAddress == null) {
            logger.warn("No Address configured");
            return false;
        }

        // build query address - we do not fix the rest ;-)
        StringBuilder queryURI = new StringBuilder("http://");
        queryURI.append(myCfg.ipAddress);
        if (myCfg.port != 80) {
            queryURI.append(":" + myCfg.port);
        }

        try {
            myUri = new URI(queryURI.toString());
        } catch (URISyntaxException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
        // check address
        if (myUri.getScheme() != null && !myUri.getScheme().toLowerCase().equals("http")) {
            logger.warn("We can only handle http direct at the moment.");
            return false;
        }

        if (myUri.getPath() != null && myUri.getPath().trim().length() > 0) {
            logger.warn("We can handle only direct access, no path possible");
            return false;
        }

        if (myUri.getPort() != 80 && myUri.getPort() != -1) {
            logger.warn("We can handle only direct access, no advanced port possible");
            return false;
        }

        if (myUri.getHost() == null) {
            logger.warn("No host part in the address found.");
            return false;
        }

        this.refreshIntervall = myCfg.refreshInterval;
        this.refreshIntervallCharging = myCfg.refreshIntervalCharging;

        if (this.refreshIntervall == null) {
            logger.warn("Refresh interval not set, setting to default 60s");
            this.refreshIntervall = 60;
        } else if (this.refreshIntervall.intValue() < 5) {
            logger.warn("To small refresh interval setting to default 60s");
            this.refreshIntervall = 60;
        }

        if (this.refreshIntervallCharging == null) {
            logger.warn("Charging refresh interval not set, setting to default 10s");
            this.refreshIntervallCharging = 10;
        } else if (this.refreshIntervallCharging.intValue() < 5) {
            logger.warn("To small charging refresh interval setting to default 10s");
            this.refreshIntervallCharging = 10;
        }

        this.deviceURI = myUri;
        return true;
    }

    private JsonObject getDeviceStatusJson() throws Exception {

        JsonObject newDeviceStatus;

        ContentResponse cr = sendRequest("/status");

        if (cr != null) {
            newDeviceStatus = new JsonParser().parse(cr.getContentAsString()).getAsJsonObject();
        } else {
            throw new Exception();
        }

        if (newDeviceStatus != null) {
            return newDeviceStatus;
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Request to the device w/o host, path must be complete w/ leading "/"
     * Method is synchronized, to avoid multiple usage at the same time.
     *
     * @param request
     * @return
     */
    private synchronized ContentResponse sendRequest(String request) {

        ContentResponse cr = null;
        String deviceAddress = null;

        if (this.deviceURI != null) {
            deviceAddress = this.deviceURI.toString();
        }

        try {

            if (this.httpClient != null) {
                this.httpClient.start();
            }

            if (this.httpClient != null) {
                cr = this.httpClient.newRequest(deviceAddress + request).method(HttpMethod.GET)
                        .timeout(10, TimeUnit.SECONDS).send();
            }

            if (this.httpClient != null) {
                this.httpClient.stop();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            // cleanup
            try {

                if (this.httpClient != null) {
                    this.httpClient.stop();
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return cr;
    }
}
