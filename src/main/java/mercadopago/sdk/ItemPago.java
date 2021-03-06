/*
 * The MIT License
 *
 * Copyright 2018 Martín Straus.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package mercadopago.sdk;

import java.math.BigDecimal;
import javax.money.MonetaryAmount;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Martín Straus <a href="mailto:martinstraus@gmail.com">martinstraus@gmail.com</a>
 */
public class ItemPago {

    private final String nombre;
    private final BigDecimal cantidad;
    private final MonetaryAmount precioUnitario;

    public ItemPago(String nombre, BigDecimal cantidad, MonetaryAmount precioUnitario) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    public JSONObject aJSON() {
        try {
            return new JSONObject()
                .put("title", nombre)
                .put("quantity", cantidad)
                .put("currency_id", precioUnitario.getCurrency().getCurrencyCode())
                .put("unit_price", precioUnitario.getNumber().numberValue(BigDecimal.class));
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}
