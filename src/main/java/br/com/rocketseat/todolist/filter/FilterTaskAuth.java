package br.com.rocketseat.todolist.filter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.rocketseat.todolist.domain.models.UserModel;
import br.com.rocketseat.todolist.domain.repositories.UserModelRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class FilterTaskAuth extends OncePerRequestFilter {

    private final UserModelRepository userModelRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var servletPath = request.getServletPath();

        if (servletPath.equals("/tasks/")) {
            // Get Auth User
            var authorization = request.getHeader("Authorization");
            var authEncoded = authorization.substring("Basic".length()).trim();
            var authDecoded = Base64.getDecoder().decode(authEncoded);
            var authString = new String(authDecoded);

            var credentials = authString.split(":");
            String username = credentials[0];
            String password = credentials[1];

            // Verify Username
            UserModel user = this.userModelRepository.findByUsername(username);

            if (user == null) {
                response.sendError(401, "User is not exists");
            } else {
                // Verify Password
                var verifyPassword = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

                if (verifyPassword.verified) {
                    request.setAttribute("idUser", user.getId());
                    filterChain.doFilter(request, response);
                } else {
                    response.sendError(401, "User is not exists");
                }
            }

        } else {
            filterChain.doFilter(request, response);
        }


    }
}
