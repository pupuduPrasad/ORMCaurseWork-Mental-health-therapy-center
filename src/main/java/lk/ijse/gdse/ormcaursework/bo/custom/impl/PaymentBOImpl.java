package lk.ijse.gdse.ormcaursework.bo.custom.impl;


import lk.ijse.gdse.ormcaursework.dto.PaymentDTO;
import lk.ijse.gdse.ormcaursework.entity.Payment;
import lk.ijse.gdse.ormcaursework.bo.custom.PaymentBO;
import lk.ijse.gdse.ormcaursework.dao.custom.PaymentDAO;
import lk.ijse.gdse.ormcaursework.dao.DAOFactory;
import lk.ijse.gdse.ormcaursework.dao.DAOType;

import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {
    PaymentDAO paymentDAO = DAOFactory.getInstance().getDAO(DAOType.PAYMENT);
    @Override
    public List<PaymentDTO> getALL() throws Exception {
        List<Payment> payments = paymentDAO.getAll();
        List<PaymentDTO> paymentDTOS = new ArrayList<>();
        for (Payment payment : payments) {
            paymentDTOS.add(new PaymentDTO(
                    payment.getPaymentID(),
                    payment.getPatientName(),
                    payment.getPaymentAmount(),
                    payment.getPaymentMethod(),
                    payment.getPaymentDate(),
                    payment.getPaymentTime()
            ));
        }
        return paymentDTOS;

    }
}
