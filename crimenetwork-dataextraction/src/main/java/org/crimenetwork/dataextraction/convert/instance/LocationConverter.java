package org.crimenetwork.dataextraction.convert.instance;

import org.crimenetwork.dataextraction.convert.Converter;
import org.crimenetwork.mongodb.entity.share.MLocation;
import org.crimenetwork.oracle.entity.share.Location;

public class LocationConverter extends Converter<MLocation, Location>{

	@Override
	protected void setManualField(Location from, MLocation to) {
		
	}

}
