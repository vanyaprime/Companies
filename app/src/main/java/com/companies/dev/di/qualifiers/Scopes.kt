package com.companies.dev.di.qualifiers

import javax.inject.Scope

/**
 * @author Ivan Prokopyev
 */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope