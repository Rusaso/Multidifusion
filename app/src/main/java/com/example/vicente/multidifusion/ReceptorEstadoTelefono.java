package com.example.vicente.multidifusion;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

public class ReceptorEstadoTelefono extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, Intent intent) {
        final TelephonyManager tm = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        tm.listen(new PhoneStateListener() {
            public void onCallStateChanged(int state,
                                           String phoneNumber) {
                // Estado y número
                // 0 = Cuelgue / Finalizar llamada
                // 1 = Recibe llamada
                // 2 = Acepta llamada
                // Sin número tél., finaliza el estado
                Log.v("xyz", state + ", " + phoneNumber);

                Intent intent = new Intent(context, ServicioClienteRest.class);
                intent.putExtra("estado", state);
                intent.putExtra("numero", phoneNumber);
                context.startService(intent);
            }
        }, PhoneStateListener.LISTEN_CALL_STATE);
    }

}
