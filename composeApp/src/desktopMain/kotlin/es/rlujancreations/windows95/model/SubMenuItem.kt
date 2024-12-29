package es.rlujancreations.windows95.model

/**
 * Created by Raúl L.C. on 29/12/24.
 */

data class SubMenuItem(val title: String, val enabled: Boolean = true, val onClick: () -> Unit)
