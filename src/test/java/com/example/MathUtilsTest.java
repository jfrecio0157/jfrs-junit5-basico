package com.example;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
/**
La anotación @TestInstance(TestInstance.Lifecycle.PER_METHOD) en JUnit 5
indica que una nueva instancia de la clase de prueba se creará para cada método de prueba.
Esto es el comportamiento predeterminado en JUnit 5 y asegura que cada prueba se ejecute
en un entorno limpio y aislado.
**/

/**
La anotación @TestInstance(TestInstance.Lifecycle.PER_CLASS) en JUnit 5,
significa que una única instancia de la clase de prueba se creará y
compartirá entre todos los métodos de prueba.
Esto puede ser útil si deseas compartir el estado entre métodos de prueba.
Puede ser útil en situaciones donde la inicialización de la clase de prueba es costosa
y deseas evitar repetirla para cada método de prueba.
**/

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@DisplayName("Test de MathUtils")
class MathUtilsTest {

    private MathUtils mathUtils;
    TestInfo testInfo;
    TestReporter testReporter;

    //Se ejecuta al inicio de todos los test. Solo una vez
    @BeforeAll
    static void initBeforeAll(){
        System.out.println("Ejecutando initBeforeAll()");
    }

    //Se ejecuta antes de cada Test
    @BeforeEach
    void initEach(TestInfo testInfo, TestReporter testReporter) {
        this.testInfo = testInfo;
        this.testReporter = testReporter;
        mathUtils = new MathUtils();
        testReporter.publishEntry("Ejecutando " + testInfo.getDisplayName() + " con tag " + testInfo.getTags());
    }

    // Con la etiqueta @Tag("Math") puedo diferenciar si un test se ejecuta o no.
    // En el menu Run -> Edit Configurations... -> en Enviroment variables poner -Dgroups=Math y solo ejecutaria los
    // test con Tag = Math
    @Test
    @DisplayName("Metodo: add()")
    @Tag("Math")
    void add() {
        int result = mathUtils.add(2,3);
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Metodo: substract()")
    @Tag("Math")
    void restar() {
         assertEquals(1, mathUtils.substract(3,2));
    }

    @Nested
    @DisplayName("with test multiply")
    @Tag("Math")
    class multiplyTest {
        @Test
        @DisplayName("when multiply two positive numbers")
        void multiplyPositive() {
            int valorExpected = 6;
            int valorCalculated = mathUtils.multiply(3,2);
            assertEquals(valorExpected,
                         valorCalculated,
                         //El mensaje en fallo se pasa como una lambda porque asi solo se monta el mensaje en caso de error
                         () -> "The value calculed " + valorCalculated + " should be a positive number, with value " + valorExpected );
        }

        @Test
        @DisplayName("Multiply negative")
        void multiplyNegative() {
            assertEquals(-6, mathUtils.multiply(3,-2));
        }

    }

    @Test
    @DisplayName("Uso del assertAll para metodo divide")
    @Tag("Math")
    void divide(){
        assertAll("Test multiple para el metodo divide",
                () -> assertEquals(5,mathUtils.divide(30,6), "la division no da el resultado esperado"),
                //Se testea que la excepcion mandada por divide cuando el denominador es 0 es ArithmeticException
                //En el caso de que sea otra excepcion distinta, se muestra el mensage en console
                () -> assertThrows(ArithmeticException.class,
                        () -> {mathUtils.divide(10,0);},
                        "La divsion por cero debe devolver una ArithmeticException"),
                () -> assertEquals(0, mathUtils.divide(0,5)),
                () -> assertThrows(ArithmeticException.class,
                        () -> {mathUtils.divide(0,0);},
                      "La divsion por cero debe devolver una ArithmeticException"),
                () -> assertEquals(-5, mathUtils.divide(5,-1))
                );

    }


    @Test
    @Tag("Math")
    void computeCircleArea() {
        DecimalFormat df = new DecimalFormat("#.00");

        assertEquals("78,54",df.format(mathUtils.computeCircleArea(5)));


    }
    @Test
    @Tag("Math")
    void computeCircleArea2() {
        assertEquals(314.1592653589793, mathUtils.computeCircleArea(10),
                "Should return right circle area");
    }

    @Test
    @Tag("Control")
    @Disabled("Se deshabilita la ejecucion de este test. Se muestra como Skipped en console")
    void testDisable (){
        System.out.println("Test que no debe ejecutarse");
    }

    @Test
    @Tag("Control")
    // @EnabledOnOs(OS.WINDOWS) -- Solo se ejecuta el test si el Sistema Operativo es Windows
    @EnabledOnOs(OS.LINUX) //Como el SO no es LINUX, se muestra el test como Skipped en console
    void testEnable (){
        System.out.println("Test para probar la etiqueta @EnableOnOs");
    }

    @Test
    @Tag("Control")
    void testAssumeTrue (){
        boolean value = false;
        assumeTrue(value);
        //Solo se muestra el mensaje si value es true
        System.out.println("Ejecutando testAssumeTrue()");

    }

    //Test con parametro de entrada.
    @ParameterizedTest
    @ValueSource(strings = {"Hello", "JUnit"})
    @Tag("Control")
    void testConParametros(String palabra) {
        assertNotNull(palabra);
    }

    //Test con parametro de entrada. Hace dos ejecuciones porque tiene dos parametros de entrada.
    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @Tag("Control")
    void testConParametrosBoolean(boolean value) {
        assumeTrue(value);
        System.out.println("Ejecutando testConParametrosBoolean() con value = " + value);

    }

}