package com.amido.stacksdemo.app.controller;

import com.amido.stacksdemo.commons.types.IHandleMessages;
import com.amido.stacksdemo.commons.types.IPersistData;
import com.amido.stacksdemo.commons.types.IProvideCloud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

  @Autowired private IProvideCloud cloudProvider;

  @Autowired private IHandleMessages messageHandler;

  @Autowired private IPersistData dataPersister;

  @GetMapping
  public ResponseEntity<String> test() {
    return ResponseEntity.ok("ACK");
  }
}
