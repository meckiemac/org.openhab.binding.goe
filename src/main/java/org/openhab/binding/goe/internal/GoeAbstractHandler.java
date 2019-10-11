package org.openhab.binding.goe.internal;

import static org.openhab.binding.goe.internal.GoeBindingConstants.*;

import java.util.List;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.smarthome.core.library.types.DecimalType;
import org.eclipse.smarthome.core.library.types.StringType;
import org.eclipse.smarthome.core.thing.Channel;
import org.eclipse.smarthome.core.thing.ChannelUID;
import org.eclipse.smarthome.core.thing.Thing;
import org.eclipse.smarthome.core.thing.binding.BaseThingHandler;
import org.eclipse.smarthome.core.types.Command;
import org.eclipse.smarthome.core.types.RefreshType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Written by a programming noob, be careful with usage.
 *
 * TODO Implement a dual mode. During charging updates and off hours updates
 * We need an internal charging flag
 *
 * @author Andreas Merk
 *
 */
public abstract class GoeAbstractHandler extends BaseThingHandler {

    // Logging
    private final Logger logger = LoggerFactory.getLogger(GoeAbstractHandler.class);

    // holding our configuration
    protected @Nullable GoeConfiguration config;

    // Our current status cache (especially for comparing status changes)
    protected @Nullable JsonObject StatusObject;

    // keeps, if avail all the old values to track changes for internal functions.
    protected @Nullable JsonObject previousStatusObject;

    // Our gson parser as permanent friend
    protected Gson gson;

    // shoudl be set to true if the car is charging, for different behavior.
    protected boolean carCharging = false;

    // if supported
    protected Number refreshIntervall;
    protected Number refreshIntervallCharging;

    /**
     * Constructor
     *
     * @param thing
     */
    public GoeAbstractHandler(Thing thing) {
        super(thing);

        // initialize the abstract handler
        gson = new Gson();
        logger.warn("Initializing AbstractHandler");
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {

        if (CAR_CONNECTION_STATUS.equals(channelUID.getId())) {
            if (command instanceof RefreshType) {

                // TODO: handle data refresh
            }

            // TODO: handle command
            // Check if command in the explicit or generic list.
            // generic list we pass with minimum check thru

            // Note: if communication with thing fails for some reason,
            // indicate that by setting the status with detail information:
            // updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
            // "Could not control device at IP address x.x.x.x");
        }
    }

    protected void sendDeviceCommand(String command, int value) throws Exception {
        sendDeviceCommand(command, String.valueOf(value));
    };

    protected void sendDeviceCommand(String command, String value) throws Exception {

        if (this.isFieldWritable(command)) {
            deviceCommand(command, value);
            return;
        }
        logger.warn("Channel {} not writable.", command);

    };

    /**
     *
     * @param command
     * @return
     */
    private boolean isFieldWritable(String command) {

        for (String field : DATA_CHANNELS_TO_WRITE) {
            if (field.equals(command)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sends command from a channel to the Charger. Must be implemented by the individual handler.
     *
     * @param command "amp", "rst", ....
     * @param value "6", ...
     *
     * @throws Exception
     */
    protected abstract void deviceCommand(String command, String value) throws Exception;

    /**
     * Callback method with new StatusObject for the implementing class.
     *
     * @param newStatusObject
     * @param fullUpdate
     */
    protected final void updateThingStatus(JsonObject newStatusObject, boolean fullUpdate) {

        if (StatusObject == null || fullUpdate) {

            if (StatusObject != null) {
                this.previousStatusObject = this.StatusObject;
            }

            this.StatusObject = newStatusObject;
            updateChannelStatus(StatusObject);

        } else {
            updateChannelStatus(getChangedStatusValues(newStatusObject));

            this.previousStatusObject = this.StatusObject;
            this.StatusObject = newStatusObject;
        }

    }

    /**
     * Internal handler for updating internal status.
     *
     * @param newStatusFields
     * @param fullUpdate
     */
    private void updateChannelStatus(JsonObject udatedStatusObject) {

        // a bit channel debugging
        List<@NonNull Channel> currentChannels = getThing().getChannels();
        for (Channel channel : currentChannels) {
            logger.warn(" {} - {} ", channel.toString(), channel.getUID().getAsString());
            logger.warn("linked: {}", isLinked(channel.getUID()));

        }

        for (String element : udatedStatusObject.keySet()) {

            ChannelUID channelUID = new ChannelUID(this.getThing().getUID(), element);

            logger.warn("Work on elment {} - {} ", element, channelUID.getAsString());

            if (isLinked(channelUID)) {

                logger.warn("Channel linked {}", element);

                Channel channel = getThing().getChannel(channelUID);

                if (channel != null) {
                    logger.warn("JsonType is {}, configured channeltype is {}",
                            udatedStatusObject.get(element).getClass().getName(), channel.getAcceptedItemType());

                    String itemType = channel.getAcceptedItemType();

                    if (itemType != null) {

                        switch (itemType) {
                            case "Number":
                                updateState(channelUID,
                                        new DecimalType(udatedStatusObject.get(element).getAsBigDecimal()));
                                logger.warn("Updating field {} with number value: ", channelUID,
                                        udatedStatusObject.get(element).getAsBigDecimal());
                                break;
                            case "String":
                                updateState(channelUID, new StringType(udatedStatusObject.get(element).getAsString()));
                                logger.warn("Updating field {} with number value: ", channelUID,
                                        udatedStatusObject.get(element).getAsString());
                                break;
                        }
                    }

                }

            }

        }
    }

    /**
     * Return all fields from the newStatusObject which are changed compared to the current StatusObject.
     *
     * @param newStatusObject
     * @return
     */
    private JsonObject getChangedStatusValues(JsonObject newStatusObject) {

        JsonObject currentStatusObject;
        JsonObject updatedFields = new JsonObject();

        if (this.StatusObject != null) {
            currentStatusObject = this.StatusObject;
        } else {
            throw new NullPointerException("newStatusObject is null");
        }

        for (String key : newStatusObject.keySet()) {

            if (currentStatusObject.get(key).equals(newStatusObject.get(key))) {

            } else {

                if (newStatusObject.get(key).isJsonPrimitive()) {
                    updatedFields.add(key, newStatusObject.get(key).getAsJsonPrimitive());
                } else if (newStatusObject.get(key).isJsonArray()) {
                    updatedFields.add(key, newStatusObject.get(key).getAsJsonArray());
                } else if (newStatusObject.get(key).isJsonObject()) {
                    updatedFields.add(key, newStatusObject.get(key).getAsJsonObject());
                } // info we don't copy JsonNull Objects ;-)

            }

        }

        return updatedFields;
    }

    /**
     * nothing todo
     */
    @Override
    public void channelLinked(ChannelUID channelUID) {
        logger.warn("Channel linked: {} ", channelUID.getAsString());

    }

    /**
     * Nothing todo
     */
    @Override
    public void channelUnlinked(ChannelUID channelUID) {
        logger.warn("Channel unlinked: {} ", channelUID.getAsString());
    }
}
