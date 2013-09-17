package com.wanshangle.api;

import com.wanshangle.api.request.BaseResponse;

import java.util.List;

/**
 * Created by Zhangneixian on 13-9-3.
 */
public interface OnApiCompleteListener {

    void onApiError(List<ApiFault> _errorList);

    void onApiDone(BaseResponse _bean);


    void onApiProcessError();
}
