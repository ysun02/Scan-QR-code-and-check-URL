/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.varvet.barcodereadersample.barcode;

import android.content.Context;
import android.util.Log;
import java.net.URL;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.barcode.Barcode;

class BarcodeTracker extends Tracker<Barcode> {
    private BarcodeGraphicTrackerCallback mListener;

    public interface BarcodeGraphicTrackerCallback {
        void onDetectedQrCode(Barcode barcode);

    }

    BarcodeTracker(Context listener) {
        mListener = (BarcodeGraphicTrackerCallback) listener;
    }

    @Override
    public void onNewItem(int id, Barcode item) {
        Log.d("my_barcode", item.displayValue);

        if (item.displayValue != null) {
            mListener.onDetectedQrCode(item);

        }


    }

    public static boolean isHttpOrHttpsUrl(Barcode item) {
        String patter = "^(http|https|ftp)://.*$";
        if (item.displayValue.matches(patter)) {
            Log.d("my_barcode1", "yes");

            return true;

        } else {
            return false;
        }
    }

}
class Test {

        /* Returns true if url is valid */
        public static boolean isValid(Barcode item)
        {
            /* Try creating a valid URL */
            try {
                new URL(item.displayValue).toURI();

                return true;
            }

            // If there was an Exception
            // while creating URL object
            catch (Exception e) {
                return false;
            }
        }
    }



