package com.example.se1417_day11_database.daos;

import android.os.Environment;

import com.example.se1417_day11_database.dtos.StudentDTO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public StudentDAO() {

    }

    public List<StudentDTO> loadFromRAW(InputStream is) throws Exception {
        List<StudentDTO> result = new ArrayList<>();
        BufferedReader br = null;
        InputStreamReader isr = null;
        String s = null;
        try {
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            StudentDTO dto = null;
            while ((s = br.readLine()) != null) {
                String[] tmp = s.split("-");
                dto = new StudentDTO(tmp[0], tmp[1], Float.parseFloat(tmp[2]));
                result.add(dto);
            }
        } finally {
            try {
                if (br != null)
                    br.close();
                if (isr != null)
                    isr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void saveToInternal(FileOutputStream fos, List<StudentDTO> list) throws Exception {
        OutputStreamWriter osw = null;
        try {
            osw = new OutputStreamWriter(fos);
            String result = "";
            for (StudentDTO dto : list) {
                result += dto.toString() + "\n";
            }
            osw.write(result);
            osw.flush();
        } finally {

        }
    }

    public List<StudentDTO> loadFromInternal(FileInputStream fis) throws Exception {
        List<StudentDTO> result = new ArrayList<>();
        String s = null;
        StudentDTO dto = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            while ((s = br.readLine()) != null) {
                String[] tmp = s.split("-");
                dto = new StudentDTO(tmp[0], tmp[1], Float.parseFloat(tmp[2]));
                result.add(dto);
            }
        } finally {

        }
        return result;
    }

    public boolean saveToExternal(List<StudentDTO> list) throws Exception {
        boolean check = false;
        OutputStreamWriter ows = null;
        FileOutputStream fos = null;
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            String realPath = sdCard.getAbsolutePath();
            File directory = new File(realPath + "/MyFiles");
            directory.mkdir();
            File f = new File(directory, "hieubd.txt");
            fos = new FileOutputStream(f);
            ows = new OutputStreamWriter(fos);
            String s = "";
            for (StudentDTO dto : list) {
                s += dto.toString() + "\n";
            }
            ows.write(s);
            ows.flush();
            check = true;
        } finally {

        }
        return check;
    }

    public List<StudentDTO> loadFromExternal() throws Exception {
        List<StudentDTO> list = new ArrayList<>();
        StudentDTO dto = null;
        String s = null;
        FileInputStream fis = null;
        BufferedReader br = null;
        InputStreamReader isr = null;
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            String realPath = sdCard.getAbsolutePath();
            File directory = new File(realPath + "/MyFiles");
            File f = new File(directory, "hieubd.txt");
            fis = new FileInputStream(f);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            while ((s = br.readLine()) != null) {
                String[] tmp = s.split("-");
                dto = new StudentDTO(tmp[0], tmp[1], Float.parseFloat(tmp[2]));
                list.add(dto);
            }
        } finally {

        }
        return list;
    }
}
