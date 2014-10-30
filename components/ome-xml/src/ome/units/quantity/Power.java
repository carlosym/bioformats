/*
 * #%L
 * The OME Data Model specification
 * %%
 * Copyright (C) 2014 Open Microscopy Environment:
 *   - Board of Regents of the University of Wisconsin-Madison
 *   - Glencoe Software, Inc.
 *   - University of Dundee
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */
/*
 * $Id$
 *
 *   Copyright 2014 University of Dundee. All rights reserved.
 *   Use is subject to license terms supplied in LICENSE.txt
 */

package ome.units.quantity;

import ome.units.quantity.Quantity;
import ome.units.unit.Unit;
import ome.units.UNITS;

/**
 * A wrapper for the Power class from the units implimintation.
 *
 * @author Andrew Patterson &nbsp;&nbsp;&nbsp;&nbsp;
 * <a href="mailto:ajpatterson@lifesci.dundee.ac.uk">ajpatterson@lifesci.dundee.ac.uk</a>
 * @version 1.0
 * <small>
 * (<b>Internal version:</b> $Revision: $Date: $)
 * </small>
 * @since 5.1
 */
public class Power extends Quantity
{
  Number value;
  Unit<ome.units.quantity.Power> unit;

  public Power(Number inValue,
    Unit<ome.units.quantity.Power> inUnit)
  {
    if (inValue == null)
    {
      throw new NullPointerException("Power: Power cannot be constructed with a null value.");
    }
    value = inValue;
    unit = inUnit;
  }

  public Number value()
  {
    return value;
  }
  
  public Number value(Unit<ome.units.quantity.Power> inUnit)
  {
    if (unit.equals(inUnit))
    {
      return value;
    }
    if (unit.isConvertible(inUnit))
    {
      return unit.convertValue(value, inUnit);
    }
    return null;
  }

  public boolean equals(Object other)
  {
    if (other == null)
    {
      return false;
    }
    if (this.getClass() != other.getClass())
    {
      return false;
    }
    Power otherPower = (Power)other;
    if (unit.equals(otherPower.unit))
    {
      // Powers use same unit so compare value
      return value.equals(otherPower.value);
    } else {
      if (unit.isConvertible(otherPower.unit))
      {
        // Powers use different compatible units so convert value then compare
        return (unit.convertValue(value, otherPower.unit)).equals(otherPower.value);
      }
    }
    return false;
  }

  public Unit<ome.units.quantity.Power> unit()
  {
    return UNITS.WATT;
  }
}