package com.metacoding.projectwc.user;

import com.metacoding.projectwc._core.util.Resp;
import com.metacoding.projectwc.worldcup.item.WorldcupItemRequest;
import com.metacoding.projectwc.worldcup.item.WorldcupItemResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipal;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;
    private final HttpSession httpSession;

    @PostMapping("/signup")
    public String signup(UserRequest.signupDTO signupDTO) {
        userService.signup(signupDTO);
        return "redirect:/login-form";
    }

    @GetMapping("/signup-form")
    public String signupForm() {
        return "signup-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "login-form";
    }

    // REST API로 만들어서 navbar.mustache에서 로그인 여부와 권한 판별 용 데이터 전송
    @GetMapping("/check-login")
    public ResponseEntity<?> checkLogin(@AuthenticationPrincipal User user) {

        System.out.println(user.getUsername());

        return new ResponseEntity<>(Resp.ok(user), HttpStatus.OK);
    }

    @GetMapping("/s/board/save-form")
    public String saveForm(@AuthenticationPrincipal User user) {
        // @AuthenticationPrincipal 어노테이션이 있으면 시큐리티가 세션에서 user 꺼내서 준다.
        System.out.println("로그인 한 username: " + user.getUsername());
        System.out.println("포스트맨에서 실행 확인");

        User user2 = (User) httpSession.getAttribute("sessionUser");
        System.out.println("두번째 username: " + user.getUsername());
        return "board/save-form";
    }

    @GetMapping("/logout")
    public String logout() {
        httpSession.invalidate();
        System.out.println("로그아웃");
        return "redirect:/login-form";
    }


//      UserResponse.CheckLoginDTO checkLoginDTO = userService.findByIdForCheck();

//    @GetMapping("/worldcups/{id}/items")
//    public ResponseEntity<?> findWorldcupItems(@PathVariable int id, @ModelAttribute WorldcupItemRequest.FindOptionsDTO findOptionsDTO) {
////        return new ResponseEntity()
//        System.out.println(findOptionsDTO);
//        WorldcupItemResponse.RenderingDTO renderingDTO = worldcupItemService.findByWorldcupIdAndNameOrderByOption(id, findOptionsDTO);
//        return new ResponseEntity(Resp.ok(renderingDTO), HttpStatus.FOUND);
//    }

}
