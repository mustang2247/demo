/**
 * Created with IntelliJ IDEA.
 * User: 刘永健
 * Date: 12-12-15
 * Time: 下午11:26
 * To change this template use File | Settings | File Templates.
 */
package test;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.rule.QueryResults;

import java.util.Collection;

public class DroolsDemo1 {
    public static void main(String[] args) {
        KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        kbuilder.add(ResourceFactory.newClassPathResource("test/test1.drl"), ResourceType.DRL);
        Collection collection = kbuilder.getKnowledgePackages();
        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
        knowledgeBase.addKnowledgePackages(collection);
        StatefulKnowledgeSession session = knowledgeBase.newStatefulKnowledgeSession();
//        session.insert(new Customer("zhang san"));
        session.insert(new Customer("li si"));
//        session.insert(new Customer("wang er"));
//        session.insert(new Customer("li xiao long"));
        session.fireAllRules();

        QueryResults qr = session.getQueryResults("query fact count");
        System.out.println("customer 数目: "+qr.size());
        session.dispose();
        System.out.println("end.");
        // why
    }
}
