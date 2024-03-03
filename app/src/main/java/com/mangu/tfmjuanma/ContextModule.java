package com.mangu.tfmjuanma;

import android.content.Context;

import com.mangu.tfmjuanma.service.FileService;
import com.mangu.tfmjuanma.service.impl.LocalFileServiceImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ContextModule {
    @Singleton
    @Provides
    FileService provideFileService(@ApplicationContext Context appContext) {
        return new LocalFileServiceImpl(appContext);
    }
}
