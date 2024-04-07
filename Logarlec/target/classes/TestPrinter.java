package Modell;
/**
 * Kiíró osztály
 */
public class TestPrinter {

    /**
     * Kiírja a legutóbb (TestOut előtt) lefutott függvény nevét, kapott paramétereit és osztályát
     */
    public static void printCallingMethod(Object... args){
        String callerName = new Throwable().getStackTrace()[2].getClassName(); //hívó objektum neve
        String className = new Throwable().getStackTrace()[1].getClassName(); //hívott objektum neve
        String methodName = new Throwable().getStackTrace()[1].getMethodName(); //függvény neve
        System.out.print(callerName + " - " + className+"::"+methodName + "(");
        //parameters
        if (args.length > 0) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i].toString());
                //ha nem az utolsó elem, írjon ki vesszőt
                if (i < args.length - 1) {
                    System.out.print(", ");
                }
            }
        }
        System.out.println(")");
    }
}
