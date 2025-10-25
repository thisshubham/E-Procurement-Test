package com.eProcurement.service;

import com.eProcurement.dto.TestResultDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestResultService {
    public TestResultDto gradeResult(Long resultId, int marks) {
        TestResultDto testResultDto = new TestResultDto();
        return testResultDto;
    }

    public List<TestResultDto> getMyResults() {
        List<TestResultDto> testResultDtos = new ArrayList<>();
        return  testResultDtos;
    }
}
