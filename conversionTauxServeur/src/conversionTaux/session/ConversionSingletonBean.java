package conversionTaux.session;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;

@Singleton
public class ConversionSingletonBean implements ConversionSingletonItf {

    int cpt=0;
    @Override
    public int getCmpt() {
        return cpt;
    }

    @Lock(LockType.WRITE)
    @Override
    public void setCmpt() {
       cpt++;
    }
}
