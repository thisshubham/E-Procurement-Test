package com.eProcurement.utility;

import com.eProcurement.constants.Commonconstants;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
@Service
public class ResponseEntity {
    public org.springframework.http.ResponseEntity<?> apiResponse(Integer responseCode, HashMap<String, Object> response, Page page) {
        try {
            response.put("timestamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSSS").format(LocalDateTime.now()));
            response.put("status", responseCode);

            if (responseCode.equals(Commonconstants.SUCCESS_STATUS)) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.OK);
            } else if (responseCode.equals(Commonconstants.FAIL_STATUS)) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
            else if (responseCode.equals(HttpStatus.OK.value())) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.OK);
            }
            else if (responseCode.equals(HttpStatus.NO_CONTENT.value())) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.NO_CONTENT);
            }
            else if (responseCode.equals(HttpStatus.CREATED.value())) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.CREATED);
            }else if (responseCode.equals(HttpStatus.INTERNAL_SERVER_ERROR.value())) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (responseCode.equals(HttpStatus.NOT_FOUND.value())) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            } else if (responseCode.equals(HttpStatus.UNAUTHORIZED.value())) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
            } else if (responseCode.equals(HttpStatus.NO_CONTENT.value())) {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.OK);
            } else {
                return new org.springframework.http.ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {

            if (response == null) {
                response = new HashMap<>();
            }
            response.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
            response.put(Commonconstants.ERROR_TAG, e.getMessage());
            return new org.springframework.http.ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public org.springframework.http.ResponseEntity<?> apiResponse(Integer responseCode, HashMap<String, Object> response) {
        return apiResponse(responseCode, response, null);
    }
}
