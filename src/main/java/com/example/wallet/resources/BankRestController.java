package com.example.wallet.resources;

import com.example.wallet.base.BaseRestController;
import com.example.wallet.dto.BankDto;
import com.example.wallet.entity.Bank;
import com.example.wallet.mapper.BankMapper;
import com.example.wallet.service.BankService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/bank")
public class BankRestController extends BaseRestController<
        BankDto, Bank , BankMapper , BankService , Long> {

    public BankRestController(BankService service, BankMapper mapper) {
        super(service, mapper);
    }
    @PostMapping
    public ResponseEntity<BankDto> add(@Valid @RequestBody BankDto bankDto) {
        Bank bank = mapper.convertDToE(bankDto);
        return ResponseEntity.ok(
                mapper.convertEToD(
                        service.saveOrUpdate(bank)
                )
        );
    }
}
