package ma.enset.metier;

import java.util.HashMap;
import java.util.Map;

public class IMetierBanqueImpl implements IMetierBanque {
    private Map<Long, Compte> comptes = new HashMap<>();
    @Override
    public void addCompte(Compte cp) {
        comptes.put(cp.getCode(), cp);
    }

    @Override
    public void verser(Long code, double montant) {
        Compte cp = comptes.get(code);
        cp.setSolde(cp.getSolde() + montant);
    }

    @Override
    public void retirer(Long code, double montant) {
        Compte cp = comptes.get(code);
        cp.setSolde(cp.getSolde() - montant);
    }

    @Override
    public Compte consulter(Long code) {
        return comptes.get(code);
    }
}
