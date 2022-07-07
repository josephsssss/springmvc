package hello.servlet.web.frontcontroller.v5.adaptor;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

public class ControllerV4HandlerAdaptor implements MyHandlerAdaptor {

    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    public ModelView handle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(req);
        HashMap<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);

        ModelView mv = new ModelView(viewName);
        mv.setModel(model);

        return mv;
    }
    private Map<String, String> createParamMap(HttpServletRequest req) {
        Map<String, String> paramMap = new HashMap<>();
        req.getParameterNames().asIterator().forEachRemaining(paramName->paramMap.put(paramName, req.getParameter(paramName)));
        return paramMap;
    }

}
