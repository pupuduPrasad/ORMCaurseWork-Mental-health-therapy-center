package lk.ijse.gdse.ormcaursework.bo.custom;



import lk.ijse.gdse.ormcaursework.bo.SuperBO;
import lk.ijse.gdse.ormcaursework.dto.ProgramDto;
import lk.ijse.gdse.ormcaursework.dto.TherapyProgramDTO;

import java.util.List;

public interface TProgramBO extends SuperBO {
    boolean saveTPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean updateTPrograms(TherapyProgramDTO therapyProgramDTO);
    boolean deleteTProgram(String therapyProgramID);
    List<ProgramDto> getALLTPrograms() throws Exception;
    String getNextProgramID();
    List<ProgramDto> getALL () throws Exception;
}
