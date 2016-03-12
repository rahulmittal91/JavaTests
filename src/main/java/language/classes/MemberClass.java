package language.classes;

/**
 * Created by rmittal1 on 3/12/16.
 * <p/>
 * Inner public classes are just another classes where the enclosing class(MemberClass here) objects have direct access to the innerclass objects
 * Innerclass objects also have full access to enclosing class objects
 * static members are not allowed in inner classes
 * same is with private inner classes
 * <p/>
 * Every instance of a member class is internally associated with an instance of the class that defines or contains the member class.
 * The methods of a member class can implicitly refer to the fields defined within the member class, as well as those defined by any enclosing class, including private fields of the enclosing class.
 */
public class MemberClass {

    private int mem1;
    public int mem2;
    protected int mem3;

    private static int staticmem1;
    public static int staticmeme2;
    protected static int staticmem3;

    private InnerClass1 innerClass1;
    private static InnerClass1 innerClass12;

    private InnerClass2 innerClass2;
    private static InnerClass2 innerClass22;

    MemberClass() {
        this.innerClass1 = new InnerClass1();
        innerClass12 = new InnerClass1();
        innerClass2 = new InnerClass2();
        innerClass22 = new InnerClass2();

    }

    private int getMem1() {
        /*
        InnerClass1.inner1mem2;  // Non static fields cannot be refrenced from a static context and statics are not allowed in ineer classes
        InnerClass1.inner1mem2;
        InnerClass1.inner1mem3;
        */

        innerClass1.inner1mem1 = 0;
        innerClass1.getInner1mem1();
        innerClass12.getInner1mem1();
        return mem1;
    }

    public int getMem2() {
        innerClass1.inner1mem2 = 0;
        innerClass1.getInner1mem2();
        innerClass12.getInner1mem2();
        return mem2;
    }

    protected int getMem3() {
        innerClass1.inner1mem3 = 0;
        innerClass12.getInner1mem3();
        innerClass1.getInner1mem3();
        return mem3;
    }

    private static int getStaticmem1() {
        return staticmem1;
    }

    public static int getStaticmeme2() {
        return staticmeme2;
    }

    protected static int getStaticmem3() {
        return staticmem3;
    }

    public class InnerClass1 {

        private int inner1mem1;
        public int inner1mem2;
        protected int inner1mem3;

        MemberClass memberClass;
        /*
        private static int staticinner1mem1;
        public static int staticinner2meme2;
        protected static int staticinner3mem3;
        */

        InnerClass1() {
            memberClass = new MemberClass();
        }

        private int getInner1mem1() {
            memberClass.mem1 = 0;
            memberClass.getMem1();

            memberClass.mem2 = 0;
            memberClass.getMem2();

            memberClass.mem3 = 0;
            memberClass.getMem3();

            MemberClass.staticmem1 = 0;

            mem1 = 0;
            staticmem1 =0;

            getMem1();
            getStaticmem1();

            return inner1mem1;
        }

        public int getInner1mem2() {
            memberClass.mem1 = 0;
            memberClass.getMem1();

            memberClass.mem2 = 0;
            memberClass.getMem2();

            memberClass.mem3 = 0;
            memberClass.getMem3();

            mem2 = 0;
            staticmeme2 =0;
            MemberClass.staticmeme2 = 1;

            getMem2();
            getStaticmeme2();

            return inner1mem2;
        }

        protected int getInner1mem3() {
            memberClass.mem1 = 0;
            memberClass.getMem1();

            memberClass.mem2 = 0;
            memberClass.getMem2();

            memberClass.mem3 = 0;
            memberClass.getMem3();

            MemberClass.staticmem3 = 0;

            mem3 = 0;
            staticmem3 =0;

            getMem3();
            getStaticmem3();

            return inner1mem3;
        }

        //        private static int staticGetInner1Mem1(){
        //            return staticinner1mem1;
        //        }
        //        public static int staticGetInner1Mem2(){
        //            return staticinner2meme2;
        //        }
        //        protected static int staticGetInne1rMem3(){
        //            return staticinner2meme2;
        //        }
    }

    private class InnerClass2 {

        private int inner2mem1;
        public int inner2mem2;
        protected int inner2mem3;

        MemberClass memberClass;

        /*
private static int staticinner1mem1;
public static int staticinner2meme2;
protected static int staticinner3mem3;
*/

        InnerClass2() {
            memberClass = new MemberClass();
        }

        private int getInner1mem1() {
            memberClass.mem1 = 0;
            memberClass.getMem1();

            memberClass.mem2 = 0;
            memberClass.getMem2();

            memberClass.mem3 = 0;
            memberClass.getMem3();

            MemberClass.staticmem1 = 0;

            mem1 = 0;
            staticmem1 =0 ;
            getMem1();
            getStaticmem1();
            return inner2mem1;
        }

        public int getInner1mem2() {
            memberClass.mem1 = 0;
            memberClass.getMem1();

            memberClass.mem2 = 0;
            memberClass.getMem2();

            memberClass.mem3 = 0;
            memberClass.getMem3();

            MemberClass.staticmeme2 = 0;

            mem2 =0;
            staticmeme2 = 0;
            getMem2();
            getStaticmeme2();
            return inner2mem2;
        }

        protected int getInner1mem3() {
            memberClass.mem1 = 0;
            memberClass.getMem1();

            memberClass.mem2 = 0;
            memberClass.getMem2();

            memberClass.mem3 = 0;
            memberClass.getMem3();

            mem3 =0 ;
            staticmem3 =0;
            getMem3();
            getStaticmem3();

            MemberClass.staticmem3 = 0;
            return inner2mem3;
        }


/*
private static int staticGetInner1Mem1(){
return staticinner1mem1;
}
public static int staticGetInner1Mem2(){
return staticinner2meme2;
}
protected static int staticGetInne1rMem3(){
return staticinner2meme2;
}
*/
    }
}
