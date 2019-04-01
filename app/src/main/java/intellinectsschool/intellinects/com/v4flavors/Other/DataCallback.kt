package intellinectsschool.intellinects.com.v4flavors.Other

/**
 * Modified by vikas 13/3/2018
 */

interface DataCallback {
    /**
     * If proper response is fetch
     */
    fun onSuccess(response: String)

    /**
     * If error is found
     */
    fun onFailure(response: String)

}
