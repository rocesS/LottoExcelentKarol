import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { PlayComponent } from './components/play/play.component';
import { ResultsComponent } from './components/results/results.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { MainComponent } from './components/main/main.component';
import {HttpClientModule} from "@angular/common/http";

const appRoutes: Routes = [
  { path: '', component: MainComponent },
  { path: 'play', component: PlayComponent },
  { path: 'results', component: ResultsComponent },
];

@NgModule({
  declarations: [
    AppComponent,
    PlayComponent,
    ResultsComponent,
    NavbarComponent,
    MainComponent,
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes, { enableTracing: true }),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
