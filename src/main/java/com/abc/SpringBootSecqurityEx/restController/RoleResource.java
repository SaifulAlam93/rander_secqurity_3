//package com.abc.SpringBootSecqurityEx.restController;
//
//import com.spring_security_app.dto.RoleDTO;
//import com.spring_security_app.service.RoleService;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import jakarta.validation.Valid;
//import org.springframework.core.MethodParameter;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.MethodArgumentNotValidException;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
//@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
//public class RoleResource {
//
//    private final RoleService roleService;
//
//    public RoleResource(final RoleService roleService) {
//        this.roleService = roleService;
//    }
//
//    @GetMapping
//    public ResponseEntity<List<RoleDTO>> getAllRoles() {
//        return ResponseEntity.ok(roleService.findAll());
//    }
//
//    @GetMapping("/{roleName}")
//    public ResponseEntity<RoleDTO> getRole(@PathVariable(name = "roleName") final String roleName) {
//        return ResponseEntity.ok(roleService.get(roleName));
//    }
//
//    @PostMapping
//    @ApiResponse(responseCode = "201")
//    public ResponseEntity<Void> createRole(@RequestBody @Valid final RoleDTO roleDTO,
//            final BindingResult bindingResult) throws MethodArgumentNotValidException {
//        if (!bindingResult.hasFieldErrors("roleName") && roleDTO.getRoleName() == null) {
//            bindingResult.rejectValue("roleName", "NotNull");
//        }
//        if (!bindingResult.hasFieldErrors("roleName") && roleService.roleNameExists(roleDTO.getRoleName())) {
//            bindingResult.rejectValue("roleName", "Exists.role.roleName");
//        }
//        if (bindingResult.hasErrors()) {
//            throw new MethodArgumentNotValidException(new MethodParameter(
//                    this.getClass().getDeclaredMethods()[0], -1), bindingResult);
//        }
//        roleService.create(roleDTO);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
//
//    @PutMapping("/{roleName}")
//    public ResponseEntity<Void> updateRole(@PathVariable(name = "roleName") final String roleName,
//            @RequestBody @Valid final RoleDTO roleDTO) {
//        roleService.update(roleName, roleDTO);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{roleName}")
//    @ApiResponse(responseCode = "204")
//    public ResponseEntity<Void> deleteRole(@PathVariable(name = "roleName") final String roleName) {
//        roleService.delete(roleName);
//        return ResponseEntity.noContent().build();
//    }
//
//}
