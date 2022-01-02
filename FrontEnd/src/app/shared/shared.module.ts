import { NgModule } from '@angular/core';
import { MaterialModule } from './modules/material.module';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CommonModule } from '@angular/common';
import { SpinnerComponent } from './components/spinner/spinner.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgxExampleLibraryModule } from '@ismaestro/ngx-example-library';
import { NgxScrollToFirstInvalidModule } from '@ismaestro/ngx-scroll-to-first-invalid';
import { LoadingPlaceholderComponent } from './components/loading-placeholder/loading-placeholder.component';
import { CapitalizeFirstPipe } from './pipes/capitalize-first.pipe';
import { LazyLoadImageModule } from 'ng-lazyload-image';
 

@NgModule({
  imports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    RouterModule,
    NgxExampleLibraryModule,
    NgxScrollToFirstInvalidModule,
    LazyLoadImageModule
  ],
  declarations: [
    SpinnerComponent,
    LoadingPlaceholderComponent,
    CapitalizeFirstPipe 
  ],
  exports: [
    CommonModule,
    MaterialModule,
    FlexLayoutModule,
    NgxExampleLibraryModule,
    SpinnerComponent,
    NgxScrollToFirstInvalidModule,
    LoadingPlaceholderComponent,
    CapitalizeFirstPipe,
    LazyLoadImageModule 
  ]
})

export class SharedModule {
}
