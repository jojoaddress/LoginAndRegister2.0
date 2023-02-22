package com.example.loginandregister;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class JsonParameter {
    private BodyDTO body;
    private HeadersDTO headers;

    public BodyDTO getBody() {
        return body;
    }

    public void setBody(BodyDTO body) {
        this.body = body;
    }

    public HeadersDTO getHeaders() {
        return headers;
    }

    public void setHeaders(HeadersDTO headers) {
        this.headers = headers;
    }

    public static class BodyDTO {
        private String code;
        private DataDTO data;
        private String requestId;
        private Boolean success;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public DataDTO getData() {
            return data;
        }

        public void setData(DataDTO data) {
            this.data = data;
        }

        public String getRequestId() {
            return requestId;
        }

        public void setRequestId(String requestId) {
            this.requestId = requestId;
        }

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public static class DataDTO {
            private ListDTO list;

            public ListDTO getList() {
                return list;
            }

            public void setList(ListDTO list) {
                this.list = list;
            }

            public static class ListDTO {
                private List<PropertyStatusInfoDTO> propertyStatusInfo;

                public List<PropertyStatusInfoDTO> getPropertyStatusInfo() {
                    return propertyStatusInfo;
                }

                public void setPropertyStatusInfo(List<PropertyStatusInfoDTO> propertyStatusInfo) {
                    this.propertyStatusInfo = propertyStatusInfo;
                }

                public static class PropertyStatusInfoDTO {
                    private String dataType;
                    private String identifier;
                    private String name;
                    private String time;
                    private String unit;
                    private String value;

                    public String getDataType() {
                        return dataType;
                    }

                    public void setDataType(String dataType) {
                        this.dataType = dataType;
                    }

                    public String getIdentifier() {
                        return identifier;
                    }

                    public void setIdentifier(String identifier) {
                        this.identifier = identifier;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }

                    public String getUnit() {
                        return unit;
                    }

                    public void setUnit(String unit) {
                        this.unit = unit;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
        }
    }

    public static class HeadersDTO {
        @JSONField(name = "access-control-allow-origin")
        private String accesscontrolalloworigin;
        private String date;
        @JSONField(name = "content-length")
        private String contentlength;
        @JSONField(name = "access-control-max-age")
        private String accesscontrolmaxage;
        @JSONField(name = "x-acs-request-id")
        private String xacsrequestid;
        @JSONField(name = "access-control-allow-headers")
        private String accesscontrolallowheaders;
        private String connection;
        @JSONField(name = "content-type")
        private String contenttype;
        @JSONField(name = "access-control-allow-methods")
        private String accesscontrolallowmethods;
        @JSONField(name = "x-acs-trace-id")
        private String xacstraceid;

        public String getAccesscontrolalloworigin() {
            return accesscontrolalloworigin;
        }

        public void setAccesscontrolalloworigin(String accesscontrolalloworigin) {
            this.accesscontrolalloworigin = accesscontrolalloworigin;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getContentlength() {
            return contentlength;
        }

        public void setContentlength(String contentlength) {
            this.contentlength = contentlength;
        }

        public String getAccesscontrolmaxage() {
            return accesscontrolmaxage;
        }

        public void setAccesscontrolmaxage(String accesscontrolmaxage) {
            this.accesscontrolmaxage = accesscontrolmaxage;
        }

        public String getXacsrequestid() {
            return xacsrequestid;
        }

        public void setXacsrequestid(String xacsrequestid) {
            this.xacsrequestid = xacsrequestid;
        }

        public String getAccesscontrolallowheaders() {
            return accesscontrolallowheaders;
        }

        public void setAccesscontrolallowheaders(String accesscontrolallowheaders) {
            this.accesscontrolallowheaders = accesscontrolallowheaders;
        }

        public String getConnection() {
            return connection;
        }

        public void setConnection(String connection) {
            this.connection = connection;
        }

        public String getContenttype() {
            return contenttype;
        }

        public void setContenttype(String contenttype) {
            this.contenttype = contenttype;
        }

        public String getAccesscontrolallowmethods() {
            return accesscontrolallowmethods;
        }

        public void setAccesscontrolallowmethods(String accesscontrolallowmethods) {
            this.accesscontrolallowmethods = accesscontrolallowmethods;
        }

        public String getXacstraceid() {
            return xacstraceid;
        }

        public void setXacstraceid(String xacstraceid) {
            this.xacstraceid = xacstraceid;
        }
    }
}
