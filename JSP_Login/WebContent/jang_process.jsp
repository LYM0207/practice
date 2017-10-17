<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, base.JangBaguni"%>

<%
   ArrayList baguni = null;
   Object obj = session.getAttribute("jang");
   
   if(obj == null)
      baguni = new ArrayList();
   else 
      baguni = (ArrayList)obj;
   
   request.setCharacterEncoding("UTF-8");
   
   String name = request.getParameter("name");
   String price = request.getParameter("price");
   System.out.println(price);
   System.out.println(name);
   
   int pos = -1;
   for(int i=0;i<baguni.size();i++){
      JangBaguni jang = (JangBaguni)baguni.get(i);
      if(jang.getName().equals(name)){
         pos = 1;
         jang.setCnt(jang.getCnt()+1);
         break;
      }
   }
   
   if(pos == -1){
      JangBaguni jang = new JangBaguni();
      jang.setName(name);
      jang.setPrice(Integer.parseInt(price.replaceAll(",", "")));
      jang.setCnt(1);
      baguni.add(jang);
   }
   
   session.setAttribute("jang", baguni);
%>
<script language="javascript">
alert("장바구니에 담았습니다.");
history.go(-1);
</script>