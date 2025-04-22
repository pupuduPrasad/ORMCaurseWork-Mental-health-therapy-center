package lk.ijse.gdse.ormcaursework.bo.custom;



import lk.ijse.gdse.ormcaursework.bo.SuperBO;
import lk.ijse.gdse.ormcaursework.dto.PaymentDTO;

import java.util.List;

public interface PaymentBO extends SuperBO {
    List<PaymentDTO> getALL() throws Exception;
}
