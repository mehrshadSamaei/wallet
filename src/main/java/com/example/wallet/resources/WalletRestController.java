package com.example.wallet.resources;

import com.example.wallet.base.BaseRestController;
import com.example.wallet.dto.WalletDto;
import com.example.wallet.dto.WalletDtoSearch;
import com.example.wallet.entity.User;
import com.example.wallet.entity.Wallet;
import com.example.wallet.entity.enums.Status;
import com.example.wallet.mapper.WalletMapper;
import com.example.wallet.mapper.WalletSearchMapper;
import com.example.wallet.service.UserService;
import com.example.wallet.service.WalletService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RequestMapping("/wallet")
@RestController
public class WalletRestController extends BaseRestController<
        WalletDto, Wallet, WalletMapper, WalletService, Long> {
    public WalletRestController(WalletService service, WalletMapper mapper, WalletSearchMapper walletSearchMapper, UserService userService) {
        super(service, mapper);
        this.walletSearchMapper = walletSearchMapper;
        this.userService = userService;
    }

    private final WalletSearchMapper walletSearchMapper;
    private final UserService userService;

    @PostMapping
    public ResponseEntity<WalletDto> add(Principal principal,@Valid @RequestBody WalletDto walletDto) {
        User user = userService.findByUsername(principal.getName());
        Wallet wallet = mapper.convertDToE(walletDto);
        wallet.setUser(user);
        wallet = service.saveOrUpdate(wallet);
        return ResponseEntity.ok(
                mapper.convertEToD(wallet)
        );
    }

    @PostMapping("/find_by_example")
    public ResponseEntity<List<WalletDtoSearch>> findByExample(@RequestBody WalletDtoSearch walletDto) {
        Wallet wallet = walletSearchMapper.convertDToE(walletDto);
        List<Wallet> walletList = service.findByExample(wallet);
        return ResponseEntity.ok(
                walletSearchMapper.convertListEToD(walletList)
        );
    }
    @GetMapping("/find_all_with_create_date")
    public ResponseEntity<List<WalletDto>> findAllWithCreateDate() {
        List<Wallet> wallets = service.findAllByOrderByCreateDateAsc();
        return ResponseEntity.ok(
                mapper.convertListEToD(wallets)
        );
    }
    @PatchMapping("/disActive_wallet")
    public ResponseEntity<WalletDto> changeStatusToNonActive(@RequestBody WalletDto walletDto){
        walletDto.setStatus(Status.nonActive);
        Wallet wallet = mapper.convertDToE(walletDto);
        return ResponseEntity.ok(
                mapper.convertEToD(
                        service.saveOrUpdate(wallet)
                )
        );
    }
    @PatchMapping
    public ResponseEntity<WalletDto> changeStatusToActive(@RequestBody WalletDto walletDto){
        walletDto.setStatus(Status.active);
        Wallet wallet = mapper.convertDToE(walletDto);
        return ResponseEntity.ok(
                mapper.convertEToD(
                        service.saveOrUpdate(wallet)
                )
        );
    }
}
