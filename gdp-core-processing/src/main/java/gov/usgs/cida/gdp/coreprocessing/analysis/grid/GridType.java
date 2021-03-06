package gov.usgs.cida.gdp.coreprocessing.analysis.grid;

import ucar.nc2.dataset.CoordinateAxis;
import ucar.nc2.dt.GridCoordSystem;
import ucar.nc2.dt.GridDatatype;

/**
 *
 * @author tkunicki
 */
public enum GridType {

    YX(),
    ZYX(),
    TYX(),
    TZYX(),
    OTHER();

    public static GridType findGridType(GridCoordSystem gcs) {
        
        if ( gcs.getEnsembleAxis() != null ||
             gcs.getRunTimeAxis() != null ||
             (gcs.hasTimeAxis() && !gcs.hasTimeAxis1D())) {
            return OTHER;
        }

        CoordinateAxis zAxis = gcs.getVerticalAxis();
        CoordinateAxis tAxis = gcs.getTimeAxis1D();
        if (zAxis != null && tAxis != null) {
            return TZYX;
        } else if (zAxis != null) {
            return ZYX;
        } else if (tAxis != null) {
            return TYX;
        } else {
            return YX;
        }

    }
    
    public static GridType findGridType(GridDatatype gdt) {
        return findGridType(gdt.getCoordinateSystem());
    }
    
}
