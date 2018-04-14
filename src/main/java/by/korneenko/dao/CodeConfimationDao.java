package by.korneenko.dao;

import by.korneenko.beans.CodeConfirmationEntity;

public interface CodeConfimationDao {
    void persist(CodeConfirmationEntity codeConfirmation);
    CodeConfirmationEntity getByKey(Long id);
    void update(CodeConfirmationEntity codeConfirmation);
    void delete(CodeConfirmationEntity codeConfirmation);
    CodeConfirmationEntity getByCode(String code);
}
