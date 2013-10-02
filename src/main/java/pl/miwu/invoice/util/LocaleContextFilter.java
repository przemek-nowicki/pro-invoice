package pl.miwu.invoice.util;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Filter used to get error message for AbstractUserDetailsAuthenticationProvider that is depending on the selected language.
 * The problem occurred because DispatcherServlet is invoked after the Security.
 * http://stackoverflow.com/questions/1373407/how-to-display-custom-error-message-in-jsp-for-spring-security-auth-exception
 *
 * Created with IntelliJ IDEA.
 * User: Przemek Nowicki (nowicki.przemek@gmail.com)
 * Date: 18.09.13
 * Time: 15:21
 */

public class LocaleContextFilter extends OncePerRequestFilter {
    private LocaleResolver localeResolver;
    public void setLocaleResolver(LocaleResolver localeResolver) {
        this.localeResolver = localeResolver;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (this.localeResolver != null) {
            final Locale locale = this.localeResolver.resolveLocale(request);
            LocaleContextHolder.setLocale(locale);
        }
        try {
            filterChain.doFilter(request, response);
        } finally {
            LocaleContextHolder.resetLocaleContext();
        }
    }
}
