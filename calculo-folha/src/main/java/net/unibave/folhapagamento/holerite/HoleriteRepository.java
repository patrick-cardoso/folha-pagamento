package net.unibave.folhapagamento.holerite;

import java.time.LocalDate;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import net.unibave.folhapagamento.base.AbstractRepository;

@Stateless
public class HoleriteRepository extends AbstractRepository<Holerite, Long> {

    public Holerite getHoleritePorData(LocalDate data) {
        TypedQuery<Holerite> query = getEntityManager().createQuery(
                "SELECT c FROM Holerite c WHERE MONTH(c.dataFolha) = :mes and DAY(c.dataFolha) = :ano", Holerite.class);

        try {
            System.out.println("data" + data.toString());
            System.out.println("mes" + data.getMonthValue());
            System.out.println("ano" + data.getYear());
            return query.setParameter("mes", data.getMonthValue())
                    .setParameter("ano", data.getYear()).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}
