package language.classes;

/**
 * Created by rmittal1 on 3/12/16.
 */
public class TestInnerClass {

    public void testMemberClass(){
        MemberClass memberClass = new MemberClass();
        MemberClass.InnerClass1 innerClass1 =  memberClass.new InnerClass1();
        //MemberClass.InnerClass2 innerClass2 ; //cannot do this
    }
}
