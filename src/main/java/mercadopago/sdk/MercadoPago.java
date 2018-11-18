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

import com.mercadopago.MP;
import java.util.Collection;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 *
 * @author Martín Straus <a href="mailto:martinstraus@gmail.com">martinstraus@gmail.com</a>
 */
public class MercadoPago {

    private final MP mp;

    public MercadoPago(Credenciales credenciales) {
        this(credenciales, false);
    }

    public MercadoPago(Credenciales credenciales, boolean sandbox) {
        this.mp = new MP(credenciales.id(), credenciales.secret());
        if (sandbox) {
            mp.sandboxMode(true);
        }
        try {
            String access = mp.getAccessToken();
            System.out.println(access);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public PreferenciaPago crearPreferenciaPago(Collection<ItemPago> items) {
        try {
            final RespuestaPreferencia respuesta = new RespuestaPreferencia(
                mp.createPreference(nuevaPreferenciaPago(items))
            );
            if (respuesta.tieneError()) {
                throw new RuntimeException(respuesta.error());
            }
            return respuesta.preferencia();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private JSONObject nuevaPreferenciaPago(Collection<ItemPago> items) {
        try {
            final JSONArray itemsJSON = new JSONArray();
            items.stream().map(ItemPago::aJSON).forEach((i) -> itemsJSON.put(i));
            return new JSONObject().put("items", itemsJSON);
        } catch (JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

}
