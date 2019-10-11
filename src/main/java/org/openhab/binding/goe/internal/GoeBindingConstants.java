/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.goe.internal;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.smarthome.core.thing.ThingTypeUID;

/**
 * The {@link GoeBindingConstants} class defines common constants, which are
 * used across the whole binding.
 *
 * @author Andreas Merk - Initial contribution
 */
@NonNullByDefault
public class GoeBindingConstants {

    private static final String BINDING_ID = "goe";

    // List of all Thing Type UIDs
    public static final ThingTypeUID THING_TYPE_HTTP_DEVICE = new ThingTypeUID(BINDING_ID, "http-device");
    public static final ThingTypeUID THING_TYPE_MQTT_DEVICE = new ThingTypeUID(BINDING_ID, "mqtt-device");
    public static final ThingTypeUID THING_TYPE_CLOUD_MQTT_DEVICE = new ThingTypeUID(BINDING_ID, "cloud-mqtt-device");
    public static final ThingTypeUID THING_TYPE_CLOUD_REST_DEVICE = new ThingTypeUID(BINDING_ID, "cloud-rest-device");

    // Status
    // calculated status
    public static final String CAR_CONNECTION_STATUS = "carconnectionstatus";
    public static final String CAR_CONNECTED_STATUS = "carconnected";
    public static final String CAR_CHARGING_STATUS = "chargingstatus";

    // direct status
    public static final String CHARGER_CAR_STATUS = "car";
    public static final String CHARGER_REBOOT_COUNTER = "rbc";
    public static final String CHARGER_REBOOT_TIMER = "rbt";
    public static final String CHARGER_CURRENT_CHARGED_DWS = "dws";
    public static final String CHARGER_TEMPERATURE = "tmp";
    public static final String CHARGER_PHASES = "pha";
    public static final String CHARGER_CABLE_AMP_CODING = "cbl";
    public static final String CHARGER_ERROR = "err";
    public static final String CHARGER_ADAPTER_IN = "adi";
    public static final String CHARGER_CURRENT_TIME = "tme";
    public static final String CHARGER_ENERGY_TOTAL = "eto";
    public static final String CHARGER_FW_UPDATE_AVAIL = "upd";
    public static final String CHARGER_UNLOOKED_BY = "uby";
    public static final String CHARGER_CURRENT_NRG = "nrg";
    public static final String CHARGER_WIFI_STATE = "wst";
    public static final String CHARGER_AWATTAR_TIME_LEFT_TO_CHARGE = "dto";

    // Card charge status
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_1 = "eca";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_2 = "ecr";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_3 = "ecd";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_4 = "ec4";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_5 = "ec5";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_6 = "ec6";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_7 = "ec7";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_8 = "ec8";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_9 = "ec9";
    public static final String CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_10 = "ec1";

    // control
    public static final String CHARGER_ALLOW_CHARGING = "alw"; // ok
    public static final String CHARGER_CURRENT_AMP_SIGNAL = "amp";
    public static final String CHARGER_SWITCH_OFF_KWH = "dwo";
    public static final String CHARGER_STOP_STATE = "stp";
    public static final String CHARGER_RESET = "rst";

    // Configuration
    public static final String CHARGER_CONF_ACCESS_STATE = "ast";
    public static final String CHARGER_CONF_TIME_OFFSET = "tof";
    public static final String CHARGER_CONF_DAYLIGHT_SAVING_OFFSET = "tds";
    public static final String CHARGER_CONF_LED_BRIGHTNESS = "lbr";
    public static final String CHARGER_CONF_ABSOLUT_MAX_AMPERE = "ama";
    public static final String CHARGER_CONF_LED_COLOR_IDLE = "cid";
    public static final String CHARGER_CONF_LED_COLOR_CHARGING = "cch";
    public static final String CHARGER_CONF_LED_COLOR_CHARGING_FINISHED = "cfi";
    public static final String CHARGER_CONF_LED_SAVE_MODE = "lse";
    public static final String CHARGER_CONF_CABLE_UNLOCK_MODE = "ust";
    public static final String CHARGER_CONF_SCHEDULER = "sch";
    public static final String CHARGER_CONF_SCHEDULER_OVERRIDE = "sdp";
    public static final String CHARGER_CONF_NORWAY_MODE = "nmo";

    public static final String CHARGER_CONF_HTTP_MODE = "r1x";
    public static final String CHARGER_CONF_TRANSPORT_ENC = "version";
    public static final String CHARGER_CONF_CLOUD_DISABLE = "cdi";

    public static final String CHARGER_CONF_WIFI_CLIENT_ENABLED = "wen";
    public static final String CHARGER_CONF_WIFI_CLIENT_SSID = "wss";
    public static final String CHARGER_CONF_WIFI_CLIENT_PASSWORD = "wke";
    public static final String CHARGER_CONF_WIFI_PASSWORD = "wak";

    public static final String CHARGER_CONF_AMPERE_LEVEL1 = "al1";
    public static final String CHARGER_CONF_AMPERE_LEVEL2 = "al2";
    public static final String CHARGER_CONF_AMPERE_LEVEL3 = "al3";
    public static final String CHARGER_CONF_AMPERE_LEVEL4 = "al4";
    public static final String CHARGER_CONF_AMPERE_LEVEL5 = "al5";

    public static final String CHARGER_CONF_KEY_CARD_NAME_1 = "rna";
    public static final String CHARGER_CONF_KEY_CARD_NAME_2 = "rnm";
    public static final String CHARGER_CONF_KEY_CARD_NAME_3 = "rne";
    public static final String CHARGER_CONF_KEY_CARD_NAME_4 = "rn4";
    public static final String CHARGER_CONF_KEY_CARD_NAME_5 = "rn5";
    public static final String CHARGER_CONF_KEY_CARD_NAME_6 = "rn6";
    public static final String CHARGER_CONF_KEY_CARD_NAME_7 = "rn7";
    public static final String CHARGER_CONF_KEY_CARD_NAME_8 = "rn8";
    public static final String CHARGER_CONF_KEY_CARD_NAME_9 = "rn9";
    public static final String CHARGER_CONF_KEY_CARD_NAME_10 = "rn1";

    public static final String CHARGER_CONF_AWATTAR_ZONE = "azo";
    public static final String CHARGER_CONF_AWATTAR_HOUR_FINISH = "afi";
    public static final String CHARGER_CONF_AWATTAR_MINIMUM_CHARGE_HOURS = "aho";

    public static final String CHARGER_CONF_CUSTOM_MQTT_ENABLE = "mce";
    public static final String CHARGER_CONF_CUSTOM_MQTT_SERVER = "mcs";
    public static final String CHARGER_CONF_CUSTOM_MQTT_PORT = "mcp";
    public static final String CHARGER_CONF_CUSTOM_MQTT_USERNAME = "mcu";
    public static final String CHARGER_CONF_CUSTOM_MQTT_PASSWORD = "mck";
    public static final String CHARGER_CONF_CUSTOM_MQTT_CONNECATION_STATUS = "mcc";

    // write-only channels
    public static final String CHARGER_CONF_SCHEDULER_SLOT1 = "r21";
    public static final String CHARGER_CONF_SCHEDULER_SLOT2 = "r31";
    public static final String CHARGER_CONF_SCHEDULER_SLOT3 = "r41";

    public static final String CHARGER_UNKNOWN_TXI = "txi";

    // properties
    public static final String CHARGER_SERIAL = "sse";
    public static final String CHARGER_FIRMWARE = "fwv";

    public static final String CHARGER_CONF_KEY_CARD_ID_1 = "rca";
    public static final String CHARGER_CONF_KEY_CARD_ID_2 = "rcr";
    public static final String CHARGER_CONF_KEY_CARD_ID_3 = "rcd";
    public static final String CHARGER_CONF_KEY_CARD_ID_4 = "rc4";
    public static final String CHARGER_CONF_KEY_CARD_ID_5 = "rc5";
    public static final String CHARGER_CONF_KEY_CARD_ID_6 = "rc6";
    public static final String CHARGER_CONF_KEY_CARD_ID_7 = "rc7";
    public static final String CHARGER_CONF_KEY_CARD_ID_8 = "rc8";
    public static final String CHARGER_CONF_KEY_CARD_ID_9 = "rc9";
    public static final String CHARGER_CONF_KEY_CARD_ID_10 = "rc1";

    // TODO load management
    public static final String CHARGER_CONF_LM_ENABLED = "loe";
    public static final String CHARGER_CONF_LM_TIME_SINCE_LAST_CHARGE = "lch";

    public static final String[] DATA_CHANNELS_TO_WRITE = {
            // control
            CHARGER_CURRENT_AMP_SIGNAL, CHARGER_ALLOW_CHARGING, CHARGER_STOP_STATE, CHARGER_SWITCH_OFF_KWH,

            // configuration
            CHARGER_CONF_WIFI_CLIENT_SSID, CHARGER_CONF_WIFI_CLIENT_PASSWORD, CHARGER_CONF_WIFI_CLIENT_ENABLED,
            CHARGER_CONF_ACCESS_STATE, CHARGER_CONF_TIME_OFFSET, CHARGER_CONF_DAYLIGHT_SAVING_OFFSET,
            CHARGER_CONF_LED_BRIGHTNESS, CHARGER_CONF_AWATTAR_ZONE, CHARGER_CONF_AWATTAR_HOUR_FINISH,
            CHARGER_CONF_ABSOLUT_MAX_AMPERE, CHARGER_CONF_AMPERE_LEVEL1, CHARGER_CONF_AMPERE_LEVEL2,
            CHARGER_CONF_AMPERE_LEVEL3, CHARGER_CONF_AMPERE_LEVEL4, CHARGER_CONF_AMPERE_LEVEL5,
            CHARGER_CONF_LED_COLOR_IDLE, CHARGER_CONF_LED_COLOR_CHARGING, CHARGER_CONF_LED_COLOR_CHARGING_FINISHED,
            CHARGER_CONF_LED_SAVE_MODE, CHARGER_CONF_CABLE_UNLOCK_MODE, CHARGER_CONF_WIFI_PASSWORD,
            CHARGER_CONF_HTTP_MODE, CHARGER_AWATTAR_TIME_LEFT_TO_CHARGE, CHARGER_CONF_NORWAY_MODE,
            CHARGER_CONF_KEY_CARD_NAME_1, CHARGER_CONF_KEY_CARD_NAME_2, CHARGER_CONF_KEY_CARD_NAME_3,
            CHARGER_CONF_KEY_CARD_NAME_4, CHARGER_CONF_KEY_CARD_NAME_5, CHARGER_CONF_KEY_CARD_NAME_6,
            CHARGER_CONF_KEY_CARD_NAME_7, CHARGER_CONF_KEY_CARD_NAME_8, CHARGER_CONF_KEY_CARD_NAME_9,
            CHARGER_CONF_KEY_CARD_NAME_10, CHARGER_CONF_CLOUD_DISABLE, CHARGER_CONF_SCHEDULER_SLOT1,
            CHARGER_CONF_SCHEDULER_SLOT2, CHARGER_CONF_SCHEDULER_SLOT3,
            // added by Andreas Merk
            CHARGER_RESET // rst=1 for device reset/reboot
    };

    public static final String[] DATA_CHANNELS_ALL = {
            // direct status
            CHARGER_CAR_STATUS, CHARGER_REBOOT_COUNTER, CHARGER_REBOOT_TIMER, CHARGER_CURRENT_CHARGED_DWS,
            CHARGER_TEMPERATURE, CHARGER_PHASES, CHARGER_CABLE_AMP_CODING, CHARGER_ERROR, CHARGER_ADAPTER_IN,
            CHARGER_CURRENT_TIME, CHARGER_ENERGY_TOTAL, CHARGER_FW_UPDATE_AVAIL, CHARGER_UNLOOKED_BY,
            CHARGER_CURRENT_NRG, CHARGER_WIFI_STATE, CHARGER_AWATTAR_TIME_LEFT_TO_CHARGE,

            CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_1, CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_2,
            CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_3, CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_4,
            CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_5, CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_6,
            CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_7, CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_8,
            CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_9, CHARGER_CONF_KEY_CARD_CHARGED_ENERGY_10,

            // control
            CHARGER_ALLOW_CHARGING, // ok
            CHARGER_CURRENT_AMP_SIGNAL, CHARGER_SWITCH_OFF_KWH, CHARGER_STOP_STATE, CHARGER_RESET,

            // Configuration
            CHARGER_CONF_ACCESS_STATE, CHARGER_CONF_TIME_OFFSET, CHARGER_CONF_DAYLIGHT_SAVING_OFFSET,
            CHARGER_CONF_LED_BRIGHTNESS, CHARGER_CONF_ABSOLUT_MAX_AMPERE, CHARGER_CONF_LED_COLOR_IDLE,
            CHARGER_CONF_LED_COLOR_CHARGING, CHARGER_CONF_LED_COLOR_CHARGING_FINISHED, CHARGER_CONF_LED_SAVE_MODE,
            CHARGER_CONF_CABLE_UNLOCK_MODE, CHARGER_CONF_SCHEDULER, CHARGER_CONF_SCHEDULER_OVERRIDE,
            CHARGER_CONF_NORWAY_MODE,

            CHARGER_CONF_HTTP_MODE, CHARGER_CONF_TRANSPORT_ENC, CHARGER_CONF_CLOUD_DISABLE,

            CHARGER_CONF_WIFI_CLIENT_ENABLED, CHARGER_CONF_WIFI_CLIENT_SSID, CHARGER_CONF_WIFI_CLIENT_PASSWORD,
            CHARGER_CONF_WIFI_PASSWORD,

            CHARGER_CONF_AMPERE_LEVEL1, CHARGER_CONF_AMPERE_LEVEL2, CHARGER_CONF_AMPERE_LEVEL3,
            CHARGER_CONF_AMPERE_LEVEL4, CHARGER_CONF_AMPERE_LEVEL5,

            CHARGER_CONF_KEY_CARD_NAME_1, CHARGER_CONF_KEY_CARD_NAME_2, CHARGER_CONF_KEY_CARD_NAME_3,
            CHARGER_CONF_KEY_CARD_NAME_4, CHARGER_CONF_KEY_CARD_NAME_5, CHARGER_CONF_KEY_CARD_NAME_6,
            CHARGER_CONF_KEY_CARD_NAME_7, CHARGER_CONF_KEY_CARD_NAME_8, CHARGER_CONF_KEY_CARD_NAME_9,
            CHARGER_CONF_KEY_CARD_NAME_10,

            CHARGER_CONF_AWATTAR_ZONE, CHARGER_CONF_AWATTAR_HOUR_FINISH, CHARGER_CONF_AWATTAR_MINIMUM_CHARGE_HOURS,

            CHARGER_CONF_CUSTOM_MQTT_ENABLE, CHARGER_CONF_CUSTOM_MQTT_SERVER, CHARGER_CONF_CUSTOM_MQTT_PORT,
            CHARGER_CONF_CUSTOM_MQTT_USERNAME, CHARGER_CONF_CUSTOM_MQTT_PASSWORD,
            CHARGER_CONF_CUSTOM_MQTT_CONNECATION_STATUS,

            // write-only channels
            CHARGER_CONF_SCHEDULER_SLOT1, CHARGER_CONF_SCHEDULER_SLOT2, CHARGER_CONF_SCHEDULER_SLOT3,

            CHARGER_UNKNOWN_TXI,

            // properties
            CHARGER_SERIAL, CHARGER_FIRMWARE,

            CHARGER_CONF_KEY_CARD_ID_1, CHARGER_CONF_KEY_CARD_ID_2, CHARGER_CONF_KEY_CARD_ID_3,
            CHARGER_CONF_KEY_CARD_ID_4, CHARGER_CONF_KEY_CARD_ID_5, CHARGER_CONF_KEY_CARD_ID_6,
            CHARGER_CONF_KEY_CARD_ID_7, CHARGER_CONF_KEY_CARD_ID_8, CHARGER_CONF_KEY_CARD_ID_9,
            CHARGER_CONF_KEY_CARD_ID_10,

            // Load management
            CHARGER_CONF_LM_TIME_SINCE_LAST_CHARGE, CHARGER_CONF_LM_ENABLED };

    public static final String[] DATA_CHANNELS_NUMBER = {

    };

    public static final String[] DATA_CHANNELS_STRING = {

    };

    public static final String[] DATA_CHANNELS_INT_ARRAY = { CHARGER_CURRENT_NRG };

}
