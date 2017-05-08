package margo.service.interior;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;
import margo.model.modelDTO.interior.InteriorDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddPatternForInterior {

    String nameFile = null;
    String nameFile01 = null;
    String nameFile02 = null;
    FileOutputStream fileOutputStream = null;

    public void checkInformations(InteriorDTO dto, String realObjectsPath, String relativeObjectsPath) throws IOException {


        if (!dto.getObjectPhotoCurtain().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }

            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(dto.getObjectPhotoCurtain().getBytes());

            nameFile = relativeObjectsPath + dto.getObjectPhotoCurtain().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain01().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain01().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }
            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(dto.getObjectPhotoCurtain01().getBytes());

            nameFile01 = relativeObjectsPath + dto.getObjectPhotoCurtain01().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain02().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain02().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }
            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(dto.getObjectPhotoCurtain02().getBytes());

            nameFile02 = relativeObjectsPath + dto.getObjectPhotoCurtain02().getOriginalFilename();
        }
    }

    public String getNameFile() {
        return nameFile;
    }

    public String getNameFile01() {
        return nameFile01;
    }

    public String getNameFile02() {
        return nameFile02;
    }
;
    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }
}


