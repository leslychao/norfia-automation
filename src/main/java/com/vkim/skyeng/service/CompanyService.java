package com.vkim.skyeng.service;

import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CompanyService {
  private static final Pattern PAYMENT_NUMBER_PATTERM = Pattern
      .compile("(?<=.)*[\\d]{4}(?=\\bот\\b)+", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
}
