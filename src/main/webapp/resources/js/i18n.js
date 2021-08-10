const i18n = [];
i18n["addTitle"] = '<spring:message code="user.add"/>';
i18n["editTitle"] = '<spring:message code="user.edit"/>';

<c:forEach var="key" items='<%=new String[]{"common.deleted","common.saved","common.enabled","common.disabled","common.errorStatus","common.confirm"}%>'>
    i18n["${key}"] = "<spring:message code="${key}"/>";
</c:forEach>