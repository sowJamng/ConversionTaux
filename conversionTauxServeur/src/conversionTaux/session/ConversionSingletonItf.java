package conversionTaux.session;

import javax.ejb.Local;

@Local
public interface ConversionSingletonItf {
    int getCmpt();
    void setCmpt();
}
