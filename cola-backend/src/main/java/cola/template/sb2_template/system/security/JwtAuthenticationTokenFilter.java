package cola.template.sb2_template.system.security;

import cola.template.sb2_template.system.config.SecurityIgnoreUrl;
import cola.template.sb2_template.system.enums.ErrorCode;
import cola.template.sb2_template.system.exception.ColaException;
import cola.template.sb2_template.system.utils.JwtUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author Cola0817
 * @date 2023/9/8 15:45
 * @since 1.0
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    private SecurityIgnoreUrl securityIgnoreUrl;

    @Override
    @Transactional
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        Stream<RequestMatcher> matchers = Arrays.stream(securityIgnoreUrl.getUrls()).map(AntPathRequestMatcher::new);
        if (matchers.anyMatch(matcher -> matcher.matches(request))) {
            filterChain.doFilter(request, response);
            return;
        }

        if (token == null) {
            throw new ColaException(ErrorCode.TOKEN_IS_EMPTY);
        }

        if (JwtUtil.verify(token) && JwtUtil.isExpired(token)) {
            throw new ColaException(ErrorCode.TOKEN_IS_EXPIRED);
        }

        if (!JwtUtil.verify(token)) {
            throw new ColaException(ErrorCode.TOKEN_IS_ILLEGAL);
        }

        String username = JwtUtil.getUsername(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);

    }
}

