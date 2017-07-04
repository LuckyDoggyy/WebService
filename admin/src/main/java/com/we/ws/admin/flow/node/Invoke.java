package com.we.ws.admin.flow.node;

import com.we.ws.admin.controller.SpringContextUtil;
import com.we.ws.admin.domain.Service;
import com.we.ws.admin.domain.ServiceParam;
import com.we.ws.admin.flow.FlowException;
import com.we.ws.admin.flow.json.State;
import com.we.ws.admin.service.WsService;
import com.we.ws.common.data.Pair;
import com.we.ws.service.client.RequestParam;
import com.we.ws.service.client.WsCaller;
import org.apache.commons.lang3.StringUtils;

import javax.print.FlavorException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author twogoods
 * @version 0.1
 * @since 2017-05-08
 */
public class Invoke extends Node {
    private String serviceId;
    private Map<String, Object> input;
    private Map<String, Object> output;

    public Invoke(String serviceId, String name, String desc, String input, String output) {
        super(name,desc);
        this.serviceId = serviceId;
        this.input = parseInOrOut(input);
        this.output = parseInOrOut(output);
    }

    @Override
    public Pair<Node, Map<String, Object>> handle(Map<String, Object> param) throws Exception {
        if (StringUtils.isEmpty(serviceId)) {
            return Pair.of(null, null);
            //匹配算法
        } else {
            WsService wsService = SpringContextUtil.getBean("wsServiceImpl");
            Service service = wsService.getServiceBySid(serviceId);
            //TODO 实际请求参数和输入参数匹配的问题
            List<ServiceParam> serviceParams = wsService.listParams(serviceId);
            List<RequestParam> list = new ArrayList<>();
            for (ServiceParam serviceParam : serviceParams) {
                list.add(new RequestParam(serviceParam.getParamName(), getValueFromOutput(serviceParam, param)));
            }
//            for (Map.Entry<String, Object> entry : input.entrySet()) {
//                String property = entry.getKey();
//                Object v = WsCaller.getMapValue(param, property);
//                if (v instanceof String) {
//                    list.add(new RequestParam(property, (String) v));
//                } else {
//                    list.add(new RequestParam(property, entry.getValue().toString()));
//                }
//            }
            List<String> out = new ArrayList<>();
            for (Map.Entry<String, Object> entry : output.entrySet()) {
                out.add(entry.getKey());
            }
            Map<String, Object> callRes = WsCaller.callInFlow(service.getUrl(), service.getTargetNamespace(), service.getMethod(), list, service.getOutput(), StringUtils.join(out, ","));
            if (callRes == null || callRes.size() == 0) {
                throw new FlowException(String.format("Invoke '%s' handled but return nothing!", name));
            }
            callRes.putAll(param);
            return Pair.of(next, callRes);
        }
    }

    private String getValueFromOutput(ServiceParam serviceParam, Map<String, Object> output) {
        String param = serviceParam.getParamName();
        Object value;
        if ((value = output.get(param)) != null) {
            return value.toString();
        }
        String alies = serviceParam.getAlies();
        if(!StringUtils.isEmpty(alies)){
            String[] allAlies=alies.split(",");
            for (String item : allAlies) {
                value=output.get(item);
                if(value!=null){
                    return value.toString();
                }
            }
        }
        return "";
    }

    public static Invoke of(State state) {
        Map<String, Map<String, String>> props = state.getProps();
        String name = props.get("text").get("value");
        String serviceId = props.get("serviceId").get("value");
        String desc = props.get("desc").get("value");
        String input = props.get("input").get("value");
        String output = props.get("output").get("value");
        return new Invoke(serviceId, name, desc, input, output);
    }

}
