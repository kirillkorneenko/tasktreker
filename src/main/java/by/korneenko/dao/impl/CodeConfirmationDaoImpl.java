package by.korneenko.dao.impl;

import by.korneenko.beans.CodeConfirmationEntity;
import by.korneenko.dao.AbstractDao;
import by.korneenko.dao.CodeConfimationDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class CodeConfirmationDaoImpl extends AbstractDao<Long, CodeConfirmationEntity> implements CodeConfimationDao {
    @Override
    public CodeConfirmationEntity getByCode(String code) {
            //**creating CriteriaBuilder**
            CriteriaBuilder builder = getCriteriaBuilder();
            CriteriaQuery<CodeConfirmationEntity> criteria = builder.createQuery(CodeConfirmationEntity.class);
            Root<CodeConfirmationEntity> codeRoot = criteria.from(CodeConfirmationEntity.class);
            criteria.select(codeRoot);

            //**Adding where clause**
            criteria.where(builder.equal(codeRoot.get("code"), code));

            return getEntityManager().createQuery(criteria).getSingleResult();
    }
}
