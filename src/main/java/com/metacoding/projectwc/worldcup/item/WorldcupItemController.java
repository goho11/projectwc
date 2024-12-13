package com.metacoding.projectwc.worldcup.item;

import com.metacoding.projectwc._core.util.Resp;
import com.metacoding.projectwc.worldcup.Worldcup;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class WorldcupItemController {
    private final WorldcupItemService worldcupItemService;

    @PostMapping("/worldcups/{id}/items")
    public ResponseEntity<?> save(@PathVariable int id, WorldcupItemRequest.SaveDTO saveDTO) {
        // TODO 유저의 월드컵 id가 맞는지 체크
        // User seesionUser = (User) session.getAttribute("sessionUser");
        // worldcupService.findById(id).getUser() 같은지 확인
        Worldcup worldcup = Worldcup.builder().id(id).build();
        worldcupItemService.save(saveDTO, worldcup);
        return new ResponseEntity(Resp.ok("됨"), HttpStatus.CREATED);
    }

    @GetMapping("/worldcups/{id}/items")
    public ResponseEntity<?> findWorldcupItems(@PathVariable int id, @ModelAttribute WorldcupItemRequest.FindOptionsDTO findOptionsDTO) {
//        return new ResponseEntity()
        System.out.println(findOptionsDTO);
        WorldcupItemResponse.RenderingDTO renderingDTO = worldcupItemService.findByWorldcupIdAndNameOrderByOption(id, findOptionsDTO);
        return new ResponseEntity(Resp.ok(renderingDTO), HttpStatus.FOUND);
    }

    @PutMapping("/worldcups/{id}/items/{itemId}")
    public ResponseEntity<?> updateName(@PathVariable int id, @PathVariable int itemId, @RequestBody  WorldcupItemRequest.UpdateNameDTO updateNameDTO) {
        worldcupItemService.updateName(itemId, updateNameDTO);
        return ResponseEntity.ok(Resp.ok("됨"));
    }

    @PostMapping("/worldcups/{id}/items/{itemId}")
    public ResponseEntity<?> updateName(@PathVariable int id, @PathVariable int itemId, WorldcupItemRequest.UpdateImgDTO updateImgDTO) {
        worldcupItemService.updateImg(itemId, updateImgDTO);
        return ResponseEntity.ok(Resp.ok("됨"));
    }
}
