/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package IGU;
/**
 *
 * @author Monkeyelgrande
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * La clase TextPrompt mostrará un mensaje sobre un componente de texto cuando
 * el contenido del campo de texto está vacío. El atributo Show se utiliza
 * para determinar la visibilidad el mensaje.
 *
 * La fuente y el color de primer plano del mensaje tendran por defecto esas propiedades
 * del componente de texto principal. Se pueden modificar estas propiedades luego de instanciar
 * la clase.
 */
public class TextPrompt extends JLabel implements FocusListener, DocumentListener {

    private static final long serialVersionUID = 1L;

    public enum Show {
        ALWAYS, FOCUS_GAINED, FOCUS_LOST;
    }

    private JTextComponent component;
    private Document document;

    private Show show;
    private boolean showPromptOnce;
    private int focusLost;

    public TextPrompt(String text, JTextComponent component) {
        this(text, component, Show.ALWAYS);
    }

    public TextPrompt(String text, JTextComponent component, Show show) {
        this.component = component;
        setShow(show);
        document = component.getDocument();

        setText(text);
        setFont(component.getFont());

//		setForeground(component.getForeground());
        setForeground(Color.gray);
//		setBorder(new EmptyBorder(component.getInsets()));
        setHorizontalAlignment(JLabel.LEADING);

        component.addFocusListener(this);
        document.addDocumentListener(this);

        component.setLayout(new BorderLayout());
        component.add(this);
        checkForPrompt();
    }

    /**
     *Metodo para cambiar el valor alpha del plano actual
     * a el color especificado.
     *
     * @param alpha
     *            rango de valores: 0 - 1.0.
     */
    public void changeAlpha(float alpha) {
        changeAlpha((int) (alpha * 255));
    }

    /**
     *Metodo para cambiar el valor alpha del plano actual
     * a el color especificado.
     *
     * @param alpha
     *            rango de valores: 0 - 255.
     */
    public void changeAlpha(int alpha) {
        alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

        Color foreground = getForeground();
        int red = foreground.getRed();
        int green = foreground.getGreen();
        int blue = foreground.getBlue();

        Color withAlpha = new Color(red, green, blue, alpha);
        super.setForeground(withAlpha);
    }

    /**
     * Metodo para cambiar el estilo de la fuente actual. Los valores de style
     * se encuentran en la clase Fuente. Los valores mas comunes podrían ser: Font.BOLD,
     * Font.ITALIC and Font.BOLD + Font.ITALIC.
     *
     * @param style
     *            valor que representa el nuevo estilo de fuente.
     */
    public void changeStyle(int style) {
        setFont(getFont().deriveFont(style));
    }

    /**
     * Obtiene el atributo show
     *
     * @return el atributo show.
     */
    public Show getShow() {
        return show;
    }

    /**
     * Guarda el atributo show, establece cuando se muestra el mensaje.
     * Los valores validos son:
     *
     * Show.AWLAYS (por defecto) - muestra siempre el mensaje.
     * Focus_GAINED - muesta el mensaje cuando se hace foco sobre el campo de texto.
     * Show.Focus_LOST - muestra mensaje cuando se pierde el foco sobre el campo
     * (y oculta el mensaje cuando se pierde el foco)
     *
     * @param show
     *            un enum valido de show
     */
    public void setShow(Show show) {
        this.show = show;
    }

    /**
     * Obtiene el atributo showPromptOnce
     *
     * @return el atributo showPromptOnce.
     */
    public boolean getShowPromptOnce() {
        return showPromptOnce;
    }

    /**
     * Muestra el mensaje una vez. Una vez que el campo de texto tiene foco o se retira,
     * luego el mensaje no se volverá a mostrar.
     *
     * @param showPromptOnce
     *            Cuando es true, el mensaje se mostrará sólo una vez, de lo contrario
     *            se mostrará repetidamente.
     */
    public void setShowPromptOnce(boolean showPromptOnce) {
        this.showPromptOnce = showPromptOnce;
    }

    /**
     * Comprueba si el mensaje debe ser visible o no. La visibilidad
     * cambiará en la actualizacion del contenido o en los cambios de focus.
     */
    private void checkForPrompt() {
        // Text has been entered, remove the prompt

        if (document.getLength() > 0) {
            setVisible(false);
            return;
        }

        // Prompt has already been shown once, remove it

        if (showPromptOnce && focusLost > 0) {
            setVisible(false);
            return;
        }

        // Check the Show property and component focus to determine if the
        // prompt should be displayed.

        if (component.hasFocus()) {
            if (show == Show.ALWAYS || show == Show.FOCUS_GAINED)
                setVisible(true);
            else
                setVisible(false);
        } else {
            if (show == Show.ALWAYS || show == Show.FOCUS_LOST)
                setVisible(true);
            else
                setVisible(false);
        }
    }

    // Implement FocusListener

    public void focusGained(FocusEvent e) {
        checkForPrompt();
    }

    public void focusLost(FocusEvent e) {
        focusLost++;
        checkForPrompt();
    }

    // Implement DocumentListener

    public void insertUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void removeUpdate(DocumentEvent e) {
        checkForPrompt();
    }

    public void changedUpdate(DocumentEvent e) {
    }
}