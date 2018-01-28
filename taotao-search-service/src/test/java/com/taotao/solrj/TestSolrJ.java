package com.taotao.solrj;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class TestSolrJ {

    @Test
    public void testAddDocument() throws Exception{
        SolrServer solrServer=new HttpSolrServer("http://192.168.10.10:8080/solr/collection1");
        SolrInputDocument document=new SolrInputDocument();
        document.addField("id","123");
        document.addField("item_title","测试商品3");
        document.addField("item_price",1000);
        solrServer.add(document);
        solrServer.commit();
    }

    @Test
    public void deleteDocumentById() throws Exception{
        SolrServer solrServer=new HttpSolrServer("http://192.168.10.10:8080/solr/collection1");
        solrServer.deleteById("test001");
        solrServer.commit();
    }

    @Test
    public void deleteDocumentByQuery() throws Exception{
        SolrServer solrServer=new HttpSolrServer("http://192.168.10.10:8080/solr/collection1");
        solrServer.deleteByQuery("item_title:测试商品3");
        solrServer.commit();
    }

    @Test
    public void searchDocument() throws Exception{
        SolrServer solrServer=new HttpSolrServer("http://192.168.10.10:8080/solr/collection1");
        SolrQuery query=new SolrQuery();
//        query.set("q","*:*");
        query.setQuery("手机");
        query.setStart(0);
        query.setRows(10);
        //默认搜索域
        query.set("df","item_keywords");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<div>");
        query.setHighlightSimplePost("</div>");
        QueryResponse queryResponse = solrServer.query(query);

        SolrDocumentList solrDocumentList = queryResponse.getResults();

        System.out.println("查询结果总记录数："+solrDocumentList.getNumFound());
        for (SolrDocument solrDocument:solrDocumentList){
            System.out.println(solrDocument.get("id"));
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String itemTitle="";
            if (list!=null&&list.size()>0){
                itemTitle = list.get(0);
            }else {
                itemTitle=(String)solrDocument.get("item_title");
            }

            System.out.println(itemTitle);
            System.out.println(solrDocument.get("item_sell_point"));
            System.out.println(solrDocument.get("item_price"));
            System.out.println(solrDocument.get("item_image"));
            System.out.println(solrDocument.get("item_category_name"));
            System.out.println("====================================");
        }

    }

}
