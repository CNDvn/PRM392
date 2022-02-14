package com.example.se1417_day11_database.daos;

import com.example.se1417_day11_database.dtos.StudentDTO;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
}
