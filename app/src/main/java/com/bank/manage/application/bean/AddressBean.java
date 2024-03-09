package com.bank.manage.application.bean;

import java.util.List;

/**
 * @Description:
 * @Author: leo.li
 * @CreateDate: 2020/3/19 16:43
 */
public class AddressBean {

    /**
     * code : 110000
     * name : 北京市
     * level : 1
     * children : [{"code":110100,"name":"北京市","level":2,"children":[{"code":110101,"name":"东城区","level":3,"children":null},{"code":110102,"name":"西城区","level":3,"children":null},{"code":110105,"name":"朝阳区","level":3,"children":null},{"code":110106,"name":"丰台区","level":3,"children":null},{"code":110107,"name":"石景山区","level":3,"children":null},{"code":110108,"name":"海淀区","level":3,"children":null},{"code":110109,"name":"门头沟区","level":3,"children":null},{"code":110111,"name":"房山区","level":3,"children":null},{"code":110112,"name":"通州区","level":3,"children":null},{"code":110113,"name":"顺义区","level":3,"children":null},{"code":110114,"name":"昌平区","level":3,"children":null},{"code":110115,"name":"大兴区","level":3,"children":null},{"code":110116,"name":"怀柔区","level":3,"children":null},{"code":110117,"name":"平谷区","level":3,"children":null},{"code":110118,"name":"密云区","level":3,"children":null},{"code":110119,"name":"延庆区","level":3,"children":null}]}]
     */

    private int code;
    private String name;
    private int level;
    private List<ChildrenBeanX> children;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<ChildrenBeanX> getChildren() {
        return children;
    }

    public void setChildren(List<ChildrenBeanX> children) {
        this.children = children;
    }

    public static class ChildrenBeanX {
        /**
         * code : 110100
         * name : 北京市
         * level : 2
         * children : [{"code":110101,"name":"东城区","level":3,"children":null},{"code":110102,"name":"西城区","level":3,"children":null},{"code":110105,"name":"朝阳区","level":3,"children":null},{"code":110106,"name":"丰台区","level":3,"children":null},{"code":110107,"name":"石景山区","level":3,"children":null},{"code":110108,"name":"海淀区","level":3,"children":null},{"code":110109,"name":"门头沟区","level":3,"children":null},{"code":110111,"name":"房山区","level":3,"children":null},{"code":110112,"name":"通州区","level":3,"children":null},{"code":110113,"name":"顺义区","level":3,"children":null},{"code":110114,"name":"昌平区","level":3,"children":null},{"code":110115,"name":"大兴区","level":3,"children":null},{"code":110116,"name":"怀柔区","level":3,"children":null},{"code":110117,"name":"平谷区","level":3,"children":null},{"code":110118,"name":"密云区","level":3,"children":null},{"code":110119,"name":"延庆区","level":3,"children":null}]
         */

        private int code;
        private String name;
        private int level;
        private List<ChildrenBean> children;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public List<ChildrenBean> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBean> children) {
            this.children = children;
        }

        public static class ChildrenBean {
            /**
             * code : 110101
             * name : 东城区
             * level : 3
             * children : null
             */

            private int code;
            private String name;
            private int level;
            private Object children;

            public int getCode() {
                return code;
            }

            public void setCode(int code) {
                this.code = code;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getLevel() {
                return level;
            }

            public void setLevel(int level) {
                this.level = level;
            }

            public Object getChildren() {
                return children;
            }

            public void setChildren(Object children) {
                this.children = children;
            }
        }
    }
}
