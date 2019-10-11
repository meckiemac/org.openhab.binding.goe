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
import org.eclipse.jdt.annotation.Nullable;

/**
 * The {@link GoeConfiguration} class contains fields mapping thing configuration parameters.
 *
 * @author Andreas Merk - Initial contribution
 */
@NonNullByDefault
public class GoeConfiguration {

    /// address in url format
    public @Nullable String ipAddress;

    public int port;

    // The serial address of the charger in the format: 00XXXX
    public @Nullable String serialNumber;

    // Refresh interval in seconds (currently http only)
    public int refreshInterval;

    // Refresh interval in seconds when car is charging
    public int refreshIntervalCharging;

    // Timout interval in seconds (currently http only)
    public int timeoutInterval;

}
