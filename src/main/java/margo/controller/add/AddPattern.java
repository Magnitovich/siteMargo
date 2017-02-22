package margo.controller.add;

import margo.model.modelDTO.allCurtainsDTO.AllFabricDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddPattern {

    String nameFile = null;
    String nameFile01 = null;
    String nameFile02 = null;
    String nameFile03 = null;
    String nameFile04 = null;
    String nameFile05 = null;
    FileOutputStream fileOutputStream = null;

    public void checkInformations(AllFabricDTO dto, String realObjectsPath, String relativeObjectsPath) throws IOException {


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
        if (!dto.getObjectPhotoCurtain03().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain03().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }
            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(dto.getObjectPhotoCurtain03().getBytes());

            nameFile03 = relativeObjectsPath + dto.getObjectPhotoCurtain03().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain04().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain04().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }

            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(dto.getObjectPhotoCurtain04().getBytes());

            nameFile04 = relativeObjectsPath + dto.getObjectPhotoCurtain04().getOriginalFilename();
        }
        if (!dto.getObjectPhotoCurtain05().isEmpty()) {
            File convertFileObjectYachts = new File(realObjectsPath +
                    dto.getObjectPhotoCurtain05().getOriginalFilename());

            if (!convertFileObjectYachts.exists()) {
                convertFileObjectYachts.createNewFile();
            }

            fileOutputStream = new FileOutputStream(convertFileObjectYachts);
            fileOutputStream.write(dto.getObjectPhotoCurtain05().getBytes());

            nameFile05 = relativeObjectsPath + dto.getObjectPhotoCurtain05().getOriginalFilename();
        }

        System.out.println("NameFILE:");
        System.out.println(nameFile+", ");
        System.out.println(nameFile01+", ");
        System.out.println(nameFile02+", ");
        System.out.println(nameFile03+", ");
        System.out.println(nameFile04+", ");
        System.out.println(nameFile05+", ");
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

    public String getNameFile03() {
        return nameFile03;
    }

    public String getNameFile04() {
        return nameFile04;
    }

    public String getNameFile05() {
        return nameFile05;
    }

    public FileOutputStream getFileOutputStream() {
        return fileOutputStream;
    }
}
